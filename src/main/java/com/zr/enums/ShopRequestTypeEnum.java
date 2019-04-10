package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 22:19
 * @Description:
 */
@Getter
public enum ShopRequestTypeEnum implements StatusEnum {
        REQUEST_NEW_SHOP(1,"店铺申请"),
        REQUEST_REOPEN_SHOP(2,"店铺申诉");

    private Integer status;
    private String message;
    ShopRequestTypeEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
