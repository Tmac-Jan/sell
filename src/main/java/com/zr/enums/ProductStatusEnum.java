package com.zr.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "在架"),
    DOWN(1, "下架")
    ;

    private Integer status;

    private String message;

    ProductStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }


}
