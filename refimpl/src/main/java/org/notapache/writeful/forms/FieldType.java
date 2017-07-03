package org.notapache.writeful.forms;

import com.fasterxml.jackson.annotation.JsonValue;

public class FieldType {
    private String type;

    public static FieldType EMAIL = new FieldType("email");
    public static FieldType SENSITIVE = new FieldType("sensitive");
    public static FieldType BOOLEAN = new FieldType("boolean");
    public static FieldType FILE = new FieldType("file");
    public static FieldType NUMBER = new FieldType("number");
    public static FieldType STRING = new FieldType("string");
    public static FieldType TEL = new FieldType("tel");

    private FieldType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
