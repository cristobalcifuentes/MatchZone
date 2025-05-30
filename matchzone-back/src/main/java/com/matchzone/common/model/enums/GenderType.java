package com.matchzone.common.model.enums;

import java.util.Arrays;
import java.util.Optional;

public enum GenderType {

    MALE(1, "masculino"),
    FEMALE(2, "femenino"),
    OTHER(3, "otro");

    private final int code;
    private final String label;

    GenderType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Optional<GenderType> fromCode(int code) {
        return Arrays.stream(values())
                .filter(g -> g.code == code)
                .findFirst();
    }
}
