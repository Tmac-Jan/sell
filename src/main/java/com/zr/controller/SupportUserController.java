package com.zr.controller;

import com.zr.config.ProjectUrlConfig;
import com.zr.constant.CookieConstant;
import com.zr.constant.RedisConstant;
import com.zr.dataobject.SupportInfo;
import com.zr.enums.ResultEnum;
import com.zr.service.SupportInfoService;
import com.zr.utils.serializer.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 16:31
 * @Description:
 */
@Controller
@RequestMapping("/support/user")
public class SupportUserController {

    @Autowired
    private SupportInfoService supportInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1. openid去和数据库里的数据匹配
        //TODO
        SupportInfo supportInfo = supportInfoService.findByOpenid(openid);
        System.out.println("```````````````````````The Support's openid is "+openid);
        if (supportInfo == null) {
            map.put("msg", ResultEnum.SUPPORT_LOGIN_FAIL.getMessage());
            map.put("url", "/sell/support/ad/list");
            return new ModelAndView("common/error");
        }

        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        //第一个参数 redis的key，第二value是openid  第三是过期时间 第四是秒
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/support/ac/list");

    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/support/ac/list");
        return new ModelAndView("common/success", map);
    }
}
