package com.zr.enums;

import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/17 10:54
 * @Description:
 */
@Getter
public enum OptionStatusEnum implements StatusEnum {
    NEVER(0, "未操作"),
    DONE(1, "已完成");
    private Integer status;

    private String message;

    OptionStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
