package com.zr.service.impl;

import com.zr.dataobject.BuyerInfo;
import com.zr.repository.BuyerInfoRepository;
import com.zr.service.BuyerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:29
 * @Description:
 */
@Service
@Slf4j
public class BuyerInfoServiceImpl implements BuyerInfoService{
    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Override
    public BuyerInfo findBuyerInfoByOpenid(String openId) {
        return buyerInfoRepository.findByOpenid(openId);
    }

    @Override
    public BuyerInfo save(BuyerInfo buyerInfo) {
        return buyerInfoRepository.save(buyerInfo);
    }
}
