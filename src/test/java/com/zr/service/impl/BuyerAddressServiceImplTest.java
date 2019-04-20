package com.zr.service.impl;

import com.zr.SellApplication;
import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import com.zr.form.BuyerAddressForm;
import com.zr.service.BuyerAddressService;
import com.zr.service.BuyerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/10 21:58
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SellApplication.class},webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BuyerAddressServiceImplTest {
    @Autowired
    private BuyerInfoService buyerInfoService;
    @Autowired
    private BuyerAddressService buyerAddressService;
    private static final String Buyer_Openid = "oG-g11sug-qZ8EDj8JAThcB6gi5Y";
    @Test
    public void findBuyerAddressByBuyerInfo() {
    }

    @Test
    //@Transactional
    public void save() {
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setBuyerAddress("北京大学");
        buyerAddress.setBuyerGender(1);
        buyerAddress.setBuyerPhone("13192266960");
        BuyerInfo buyerInfo = buyerInfoService.findBuyerInfoByOpenid(Buyer_Openid);
        buyerAddress.setBuyerInfo(buyerInfo);
        buyerAddress.setOpenid(buyerInfo.getOpenid());
        Assert.assertNotEquals(null,buyerAddressService.save(buyerAddress));;
    }

    @Test
    public void delete() {

    }

    @Test
    public void findAll() {
        BuyerInfo buyerInfo = buyerInfoService.findBuyerInfoByOpenid(Buyer_Openid);
        PageRequest pageRequest = new PageRequest(0,10);
        Page<BuyerAddressForm> buyerAddressFormPage = buyerAddressService.findBuyerAddressByBuyerInfo(buyerInfo,pageRequest);
        buyerAddressFormPage.getContent().stream().forEach(e->System.out.println(e));
        Assert.assertNotEquals(0,buyerAddressFormPage.getTotalElements());
    }
}