package com.github.transformeli.desafiospring.enums;

import lombok.Getter;

public enum ParamOrderEnum {

    ASCENDING_ALPHA("Ascending alphabet"),
    DESCENDING_ALPHA("Descending alphabet"),
    LOWER_PRICE("Lower Price"),
    HIGHER_PRICE("Higher Price");

    @Getter
    private String name;

    ParamOrderEnum(String name) {
        this.name = name;
    }

    public static ParamOrderEnum valueOf(int orderId) {
        return ParamOrderEnum.values()[orderId];
    }
}
