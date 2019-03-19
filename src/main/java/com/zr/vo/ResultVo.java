package com.zr.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 16:38
 * @Description:
 */
@Data
public class ResultVo <T> implements Serializable {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}
