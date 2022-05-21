package org.example.entity;

import java.util.Objects;

public enum Gender {
    male("男"),
    female("女");

    private String value;
    private Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromString(String value){
        Objects.requireNonNull(value, "value can not be null");
        Gender gender = null;
        if("男".equals(value)){
            gender = male;
        }
        else if("女".equals(value)){
            gender = female;
        }
        return gender;
    }
}