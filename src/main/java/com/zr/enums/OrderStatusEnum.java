package com.zr.enums;

import lombok.Getter;


@Getter
public enum OrderStatusEnum implements StatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer status;

    private String message;

    OrderStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
