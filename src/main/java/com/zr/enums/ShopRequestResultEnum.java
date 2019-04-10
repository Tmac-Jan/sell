package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 21:53
 * @Description:
 */
@Getter
public enum ShopRequestResultEnum implements StatusEnum {
     REQUEST_AGREE(1,"审核通过"),
     REQUEST_REFECT(2,"审核不通过"),
     REQUEST_HANDLE(3,"处理中"),
    REQUEST_CANCEL(4,"卖家取消"),
     REQUEST_NEW(0,"新建"),;
    private Integer status;
    private String message;

   ShopRequestResultEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
