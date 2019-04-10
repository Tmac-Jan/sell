package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/30 09:42
 * @Description:
 */
@Getter
public enum ShopOpenStatus implements StatusEnum {
    SHOP_BLOCK(0,"打烊"),
    SHOP_OK(1,"营业");
    private Integer status;
    private String message;
    ShopOpenStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
