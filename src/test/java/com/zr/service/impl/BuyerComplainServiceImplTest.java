package com.zr.service.impl;

import com.zr.dataobject.BuyerComplain;
import com.zr.enums.BuyerComplainEnum;
import com.zr.repository.BuyerComplainRepository;
import com.zr.service.BuyerComplainService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:55
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerComplainServiceImplTest {
    @Autowired
    private BuyerComplainService buyerComplainService;

    @Autowired
    private BuyerComplainRepository buyerComplainRepository;
    private static final String buyer_Openid = "oG-g11sug-qZ8EDj8JAThcB6gi5Y";
    private static final String order_Id = "011223554";
    @Test
    public void findByBuyerOpenid() {
    }

    @Test
    public void findByBuyerShopId() {
    }

    @Test
    //@Transactional
    public void save(){
        BuyerComplain buyerComplain = new BuyerComplain();
        buyerComplain.setBuyerId(1);
        buyerComplain.setOrderId(order_Id);
        buyerComplain.setBuyerOpenid(buyer_Openid);
        buyerComplain.setShopId("201931901");
        buyerComplain.setBuyerComplain("草泥马，太几把难吃了！");
        buyerComplain.setComplainFile("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1845768900,2545680961&fm=26&gp=0.jpg");
        buyerComplain.setComplainPhoto("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1845768900,2545680961&fm=26&gp=0.jpg");
        buyerComplain.setState(BuyerComplainEnum.BUYER_REPLY.getStatus());
        buyerComplainRepository.save(buyerComplain);

    }
}