package com.zr.service.impl;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:46
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerInfoServiceImplTest {

    public  static  final  String openid = "12345abc";

    @Autowired
    private  BuyerInfoServiceImpl buyerInfoService;
    @Test
    @Transactional
    public void findBuyerInfoByOpenId() {
       BuyerInfo buyerInfos =  buyerInfoService.findBuyerInfoByOpenid(openid);
       List<BuyerAddress> buyerAddresses = buyerInfos.getBuyerAddressList();
        Assert.assertEquals(openid, buyerInfos.getOpenid());
    }

    @Test
    @Transactional
    public  void save(){
//        BuyerInfo buyerInfo = new BuyerInfo();
//      //  buyerInfo.setId(new Integer(2));
//        buyerInfo.setOpenid("abc");
//
//        BuyerAddress buyerAddress = new BuyerAddress();
//      //  buyerAddress.setId(new Integer(2));
//        buyerAddress.setBuyerGender(new Integer(1));
//        buyerAddress.setBuyerAddress("北京市萨达萨达");
//        buyerAddress.setBuyerPhone("13192266960");
//        buyerAddress.setOpenid(buyerInfo.getOpenid());
//
//        buyerInfo.getBuyerAddressList().add(buyerAddress);
//        buyerInfoService.save(buyerInfo);
    }
}