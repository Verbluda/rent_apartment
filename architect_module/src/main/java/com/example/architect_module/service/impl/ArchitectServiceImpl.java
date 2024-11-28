package com.example.architect_module.service.impl;

import com.example.architect_module.model.ArchitectRequestDto;
import com.example.architect_module.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ArchitectServiceImpl implements ArchitectService {

    private final FlywayMigrationService flywayMigrationService;
    private final JdbcTemplate jdbcTemplate;

    public static final String FILENAME_TEMPLATE = "V%d__%s_%s.sql";
    public static final String PATH_OF_MIGRATION_FILE = "C:\\Users\\Lucy\\IdeaProjects\\rent_apartment\\architect_module\\src\\main\\resources\\db\\migration\\%s";
    public static final String CREATE_SCRIPT_START = "CREATE TABLE IF NOT EXISTS %s (\n    ";
    public static final String COLUMNS = "    %s %s,\n";
    public static final String CREATE_SCRIPT_END = ");\n\nCREATE SEQUENCE %s_sequence;";
    public static final String UPDATE_SCRIPT_START = "ALTER TABLE IF EXISTS %s";
    public static final String UPDATE_SCRIPT_UPDATE_TYPE = "\nALTER COLUMN %s TYPE %s,";
    public static final String UPDATE_SCRIPT_END = ";";
    public static final String DELETE_SCRIPT = "DROP TABLE IF EXISTS %s;\n DROP SEQUENCE IF EXISTS %s_sequence;";

    @Override
    public void createMigrationFile(ArchitectRequestDto architectRequestDto, Model model) {
        String operation = architectRequestDto.getOperation();
        String name = architectRequestDto.getTableName();
        Map<String, String> newColumns = architectRequestDto.getValues();

        int version = calculateVersionOfScript();

        String filename = String.format(FILENAME_TEMPLATE, version, operation, name);
        String filePath = String.format(PATH_OF_MIGRATION_FILE, filename);

        try {
            StringBuilder script = new StringBuilder();
            switch (operation) {
                case "create":
                    writeInFileCreateScript(script, name, newColumns);
                    break;
                case "update":
                    writeInFileUpdateScript(script, name, newColumns);
                    break;
                case "delete":
                    writeInFileDeleteScript(script, name);
                    break;
                default:
                    throw new RuntimeException("неизвестная операция с базой данных");
            }

            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(script.toString());
                fileWriter.flush();
                flywayMigrationService.migrate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMainPage(Model model) {
        String query = "SELECT description FROM flyway_schema_history";
        List<String> rowListTableName = jdbcTemplate.query(query, new SingleColumnRowMapper<>(String.class));
        List<String> listTableName = rowListTableName.stream()
                .map(s -> s.substring(s.indexOf(" ") + 1))
                .map(s -> s.replace(" ","_"))
                .collect(Collectors.toList());
        model.addAttribute("listTableName", listTableName);
    }

    @Override
    public Map<String, String> getColumnsData(String tableName) {
        Map<String, String> columnsData = new HashMap<>();
        String query = "SELECT COLUMN_NAME, UDT_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '%s'";
        List<Map<String, Object>> columnList = jdbcTemplate.queryForList(String.format(query, tableName));
        for (Map<String, Object> column : columnList) {
            String columnName = column.get("column_name").toString();
            String columnType = column.get("udt_name").toString();
            columnsData.put(columnName, columnType);
        }
        return columnsData;
    }

    private int calculateVersionOfScript() {
        String query = "SELECT COUNT(version) FROM flyway_schema_history";
        if (isNull(jdbcTemplate.queryForObject(query, Integer.class))) {
            return 1;
        }
        return jdbcTemplate.queryForObject(query, Integer.class) + 1;
    }

    private void writeInFileCreateScript(StringBuilder script, String tableName, Map<String, String> newColumns) {
        script.append(String.format(CREATE_SCRIPT_START, tableName));
        for (Map.Entry<String, String> column : newColumns.entrySet()) {
            script.append(String.format(COLUMNS, column.getKey(), column.getValue()));
        }
        script.delete(script.length() - 2, script.length() - 1);
        script.append(String.format(CREATE_SCRIPT_END, tableName));
    }

    private void writeInFileUpdateScript(StringBuilder script, String tableName, Map<String, String> newColumns) {
        script.append(String.format(UPDATE_SCRIPT_START, tableName));

        Map<String, String> columnsToUpdateType = new HashMap<>();

        String query = "SELECT COLUMN_NAME, UDT_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '%s'";
        List<Map<String, Object>> oldColumns = jdbcTemplate.queryForList(String.format(query, tableName));

        for (Map<String, Object> oldColumn : oldColumns) {
            String oldColumnName = oldColumn.get("column_name").toString();
            String oldColumnType = oldColumn.get("udt_name").toString();

            String newColumnType = newColumns.get(oldColumnName);
            if (!newColumnType.equals(oldColumnType)) {
                columnsToUpdateType.put(oldColumnName, newColumnType);
            }

        }

        if (!columnsToUpdateType.isEmpty()) {
            for (Map.Entry<String, String> columnToUpdateType : columnsToUpdateType.entrySet()) {
                script.append(String.format(UPDATE_SCRIPT_UPDATE_TYPE, columnToUpdateType.getKey(), columnToUpdateType.getValue()));
            }
            script.deleteCharAt(script.length() - 1);
        }
        script.append(UPDATE_SCRIPT_END);
    }

    private void writeInFileDeleteScript(StringBuilder script, String tableName) {
        script.append(String.format(DELETE_SCRIPT, tableName, tableName));
    }
}
