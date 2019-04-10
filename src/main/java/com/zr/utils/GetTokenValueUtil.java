package com.zr.utils;

import com.zr.constant.CookieConstant;
import com.zr.constant.RedisConstant;
import com.zr.exception.SellerAuthException;
import com.zr.utils.serializer.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/25 13:40
 * @Description:
 */

@Component
@Slf4j
public class GetTokenValueUtil {
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;
    private static  StringRedisTemplate redisTemplate;
    @PostConstruct
    void init(){
        redisTemplate=stringRedisTemplate;
    }
    public static String getToken(HttpServletRequest request){
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthException();
        }
        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthException();
        }
        return  tokenValue;
    }
}
