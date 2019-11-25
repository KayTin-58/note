package com.tool;

public enum DataBaseType {
    HIVE("Hive"), MYSQL("MySql");
    private final String type;

    DataBaseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
