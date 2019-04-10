package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 22:50
 * @Description:
 */
@Getter
public enum  ShopStatusEnum implements StatusEnum {
    SHOP_BLOCK(0,"封锁"),
    SHOP_OK(1,"正常");
    private Integer status;
    private String message;
    ShopStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
