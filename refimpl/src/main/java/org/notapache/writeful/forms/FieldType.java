package org.notapache.writeful.forms;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

import java.util.Map;

public enum FieldType {
    //private static Map<String, FieldType> typeMap = Maps.newHashMap();


    EMAIL ("email"),
    SENSITIVE ("sensitive"),
    BOOLEAN ("boolean"),
    FILE ("file"),
    NUMBER ("number"),
    STRING ("string"),
    TEL ("tel"),
    DATE ("date");

    private final String type;
/*
    public static final FieldType EMAIL = new FieldType("email");
    public static final FieldType SENSITIVE = new FieldType("sensitive");
    public static final FieldType BOOLEAN = new FieldType("boolean");
    public static final FieldType FILE = new FieldType("file");
    public static final FieldType NUMBER = new FieldType("number");
    public static final FieldType STRING = new FieldType("string");
    public static final FieldType TEL = new FieldType("tel");
    public static final FieldType DATE = new FieldType("date");
*/

    private FieldType(String type) {
        this.type = type;
       // typeMap.put(type, this);
    }

    @JsonValue
    public String getType() {
        return type;
    }

//    public static boolean isValidType(String type) {
     //   return typeMap.containsKey(type);
    //}
    //public static FieldType getType(String type) {
    //    return typeMap.get(type);
    //}
}
