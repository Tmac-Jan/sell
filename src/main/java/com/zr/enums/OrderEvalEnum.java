package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:41
 * @Description:
 */
@Getter
public enum OrderEvalEnum implements StatusEnum {
    BUYER_REPLY(1,"买家已评价"),
    SHOP_REPLY(2,"商家已回复"), ;

    private Integer status;
    private String message;

    OrderEvalEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }}
