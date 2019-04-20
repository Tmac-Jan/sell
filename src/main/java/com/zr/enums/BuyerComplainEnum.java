package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 21:04
 * @Description:
 */
@Getter
public enum BuyerComplainEnum implements StatusEnum {
    BUYER_REPLY(1, "买家已投诉"),
    SHOP_REPLY(2, "商家已回复"),
    SUPPORT_REPLY(3,"客服已回复"),;

    private Integer status;
    private String message;

    BuyerComplainEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }}
