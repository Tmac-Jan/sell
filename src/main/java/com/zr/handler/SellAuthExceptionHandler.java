package com.zr.handler;

import com.zr.config.ProjectUrlConfig;
import com.zr.exception.BlockException;
import com.zr.exception.NoShopException;
import com.zr.exception.SellException;
import com.zr.exception.SellerAuthException;
import com.zr.utils.ResultVoUtil;
import com.zr.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 12:36
 * @Description:
 */
@ControllerAdvice
public class SellAuthExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login

    //https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http%3a%2f%2fsell.springboot.cn%2fsell%2fqr%2foTgZpwWy9AifxZZKQGbFAlJfVEkw&response_type=code&scope=snsapi_login&state=http%3a%2f%2fsellgio.natapp1.cc%2fsell%2fwechat%2fqrUserInfo
    @ExceptionHandler(value = SellerAuthException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        String returnUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87" +
                "&redirect_uri=http%3a%2f%2fsell.springboot.cn%2fsell%2fqr%2foTgZpwWy9AifxZZKQGbFAlJfVEkw" +
                "&response_type=code&scope=snsapi_login&state=http%3a%2f%2fsellgio.natapp1.cc%2fsell%2fwechat%2fqrUserInfo";

        Map<String,Object> map = new HashMap<>();
        map.put("msg","没有权限访问！将跳转至微信授权页面！");
        map.put("url",returnUrl);
        return new ModelAndView("auth/noAuth",map);

    }
    @ExceptionHandler(value = BlockException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerBlockException() {
        String returnUrl = "http://www.baidu.com";
        Map<String,Object> map = new HashMap<>();
        map.put("msg","店铺已被封禁访问！将跳转至微信授权页面！");
        map.put("url",returnUrl);
        return new ModelAndView("banned/banned",map);

    }
    @ExceptionHandler(value = NoShopException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerNoShopException() {
        String returnUrl = "http://www.baidu.com";
        Map<String,Object> map = new HashMap<>();
        map.put("msg","你还没有线上店铺哦！");
        map.put("url",returnUrl);
        return new ModelAndView("auth/noShop",map);

    }
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handlerSellerException(SellException e) {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

//    @ExceptionHandler(value = ResponseBankException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public void handleResponseBankException() {
//
//    }
}
