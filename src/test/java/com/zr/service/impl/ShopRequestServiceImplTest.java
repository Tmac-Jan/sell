package com.zr.service.impl;

import com.zr.dataobject.ShopRequest;
import com.zr.service.ShopRequestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 10:42
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShopRequestServiceImplTest {

    @Autowired
    private  ShopRequestService shopRequestService;
    @Test
    public void findById() {
         ShopRequest shopRequest = shopRequestService.findById("2019032202");
         Assert.assertNotEquals(null,shopRequest);
    }

    @Test
    public void agreeRequest() {
    }

    @Test
    public void findAllOrderByUpdateTimeDesc() {
        PageRequest request = new PageRequest(0,2);
        Page<ShopRequest> shopRequests = shopRequestService.findAllOrderByUpdateTimeDesc(request);
        Assert.assertTrue("查询所有的申请列表", shopRequests.getTotalElements() > 0);
    }
}