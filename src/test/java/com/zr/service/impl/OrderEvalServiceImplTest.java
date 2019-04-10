package com.zr.service.impl;

import com.zr.dataobject.OrderEval;
import com.zr.service.OrderEvalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/24 13:18
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderEvalServiceImplTest {

    @Autowired
    private OrderEvalService orderEvalService;

    private static final String EVAL_ID = "2019032401";
    @Test
    public void findOrderEvalByBuyerId() {

    }

    @Test
    public void findOrderEvalByShopId() {
    }

    @Test
    public void findOne() {
        OrderEval orderEval = orderEvalService.findOne(EVAL_ID);
        System.out.println(orderEval.toString());
        assertNotEquals(null,orderEval);
    }

    @Test
    public void getOrderEvalFormByShopId() {
    }

    @Test
    public void save() {
    }
}