package com.example.architect_module.service.impl;

import com.example.architect_module.model.ArchitectRequestDto;
import com.example.architect_module.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ArchitectServiceImpl implements ArchitectService {

    private final Flyway flyway;
    private final JdbcTemplate jdbcTemplate;

    public static final String FILENAME_TEMPLATE = "V%d__%s_%s.sql";
    public static final String PATH_OF_MIGRATION_FILE = "C:\\Users\\Lucy\\IdeaProjects\\rent_apartment\\architect_module\\src\\main\\resources\\db\\migration\\%s";
    public static final String CREATE_SCRIPT_START = "CREATE TABLE IF NOT EXISTS %s (\n    id int8 PRIMARY KEY";
    public static final String COLUMNS = ",\n    %s %s";
    public static final String CREATE_SCRIPT_END = ");\n\nCREATE SEQUENCE %s_sequence START 1 INCREMENT 1;";
    public static final String UPDATE_SCRIPT_START = "ALTER TABLE IF EXISTS {";
    public static final String UPDATE_SCRIPT_UPDATE_TYPE = "\nALTER %s %s";
    public static final String UPDATE_SCRIPT_END = "\n};";
    public static final String DELETE_SCRIPT = "DROP TABLE IF EXIST %s;\n DROP SEQUENCE IF EXIST %s_sequence";

    @Override
    public void createMigrationFile(ArchitectRequestDto architectRequestDto) {
        String operation = architectRequestDto.getOperation();
        String name = architectRequestDto.getTableName();
        Map<String, String> newColumns = architectRequestDto.getValues();

        int version = calculateVersionOfScript();

        String filename = String.format(FILENAME_TEMPLATE, version, operation, name);
        String filePath = String.format(PATH_OF_MIGRATION_FILE, filename);

        Path path = Paths.get(filePath);
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

            byte[] bs = script.toString().getBytes();
            Files.write(path, bs);
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMainPage(Model model) {
        String query = "SELECT description FROM flyway_schema_history";
        List<String> rowlistTableName = jdbcTemplate.query(query, new SingleColumnRowMapper<>(String.class));
        List<String> listTableName = rowlistTableName.stream().map(s -> s.substring(s.indexOf(" ") + 1)).collect(Collectors.toList());
        model.addAttribute("listTableName", listTableName);
    }

    private int calculateVersionOfScript() {
        String query = "SELECT MAX(version) FROM flyway_schema_history";
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
        script.append(String.format(CREATE_SCRIPT_END, tableName));
    }

    private void writeInFileUpdateScript(StringBuilder script, String tableName, Map<String, String> newColumns) {
        script.append(String.format(UPDATE_SCRIPT_START, tableName));

        Map<String, String> columnsToUpdateType = new HashMap<>();

        String query = "SELECT COLUMN_NAME, UDT_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '%s'";
        List<Map<String, Object>> oldColumns = jdbcTemplate.queryForList(String.format(query, "address"));

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
                script.append(" | ");
            }
        }
        script.append(UPDATE_SCRIPT_END);
    }

    private void writeInFileDeleteScript(StringBuilder script, String tableName) {
        script.append(String.format(DELETE_SCRIPT, tableName, tableName));
    }
}
