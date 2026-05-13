package com.chance.litestock.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    WAIT_PAY("待支付"),

    PAID("已支付"),

    CLOSED("已关闭"),

    ;

    private final String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }
}
