package com.zr.service.impl;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.service.SellerInfoService;
import com.zr.service.ShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 22:06
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShopInfoServiceImplTest {

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private SellerInfoService sellerInfoService;

    private static final String SELLER_ID = "2110";
    @Test
    public void findBySellerInfo() {
          SellerInfo sellerInfo = sellerInfoService.findSellerInfoById(SELLER_ID);
          ShopInfo shopInfo = shopInfoService.findBySellerInfo(sellerInfo);
          System.out.println(shopInfo.toString());
        Assert.assertNotEquals(null, shopInfo );
    }
}