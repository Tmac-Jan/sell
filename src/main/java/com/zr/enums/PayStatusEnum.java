package com.zr.enums;

import lombok.Getter;


@Getter
public enum PayStatusEnum implements StatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer status;

    private String message;

    PayStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
