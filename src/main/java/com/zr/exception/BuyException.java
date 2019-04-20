package com.zr.exception;

import com.zr.enums.ResultEnum;
import lombok.Getter;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/10 20:59
 * @Description:
 */
@Getter
public class BuyException extends RuntimeException {
    private Integer code;

    public BuyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getStatus();
    }

    public BuyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
