package com.scinan.utils;

/**
 * Created by Alex on 2017/2/16.
 */
public enum TypeEnum {
    ONE("MD", "/%s/S03/1/"),
    TWO("ME", "/%s/S05/1/"),
    THREE("WO", "/%s/S07/1/"),
    FOUR("FO", "/%s/S08/1/");

    TypeEnum(String flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    private String flag;
    private String value;

    public String getFlag() {
        return flag;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String flag) {
        for (TypeEnum t : TypeEnum.values()) {
            if (flag.equals(t.getFlag())) {
                return t.getValue();
            }
        }
        return null;
    }

}
