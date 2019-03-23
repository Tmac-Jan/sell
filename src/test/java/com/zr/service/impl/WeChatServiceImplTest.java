package com.zr.service.impl;

import com.zr.dto.OrderDTO;
import com.zr.service.OrderService;
import com.zr.service.WeChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 16:25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeChatServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeChatMessageService weChatMessageService;
    @Test
    public void orderStatus() throws  Exception {
        OrderDTO orderDTO =  orderService.findOne("011223554");
        weChatMessageService.orderStatus(orderDTO);
    }
}