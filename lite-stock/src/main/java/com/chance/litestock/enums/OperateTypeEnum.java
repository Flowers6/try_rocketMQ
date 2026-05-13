package com.chance.litestock.enums;

import lombok.Getter;

@Getter
public enum OperateTypeEnum {

    FREEZE("冻结库存"),

    RELEASE("释放库存"),

    DEDUCT("正式扣减库存"),

    ;

    private final String description;

    OperateTypeEnum(String description) {
        this.description = description;
    }

}
