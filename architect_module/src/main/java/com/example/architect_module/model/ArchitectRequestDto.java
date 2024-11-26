package com.example.architect_module.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ArchitectRequestDto {

    private String tableName;
    private String operation;
    private Map<String, String> values;
}
