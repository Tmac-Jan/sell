package com.zr.aop;

import com.zr.constant.CookieConstant;
import com.zr.constant.RedisConstant;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.exception.BlockException;
import com.zr.exception.NoShopException;
import com.zr.exception.SellerAuthException;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import com.zr.utils.serializer.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 12:28
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private SellerInfoService sellerInfoService;

    @Pointcut("execution(public * com.zr.controller.Seller*.*(..))" +
            "&& !execution(public * com.zr.controller.SellerUserController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

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
        //检验是否具有店铺或者店铺是否被封锁
        String openid = tokenValue;
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
        ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
        if (shopInfo==null){
            log.warn("卖家:"+sellerInfo.getOpenid()+" 越权操作");
            throw  new NoShopException();
        }
        if (shopInfo.getShopBlock()==0){
            log.warn("店铺:"+shopInfo.getShopName()+" 已被封锁");
            throw  new BlockException();
        }

    }
}
