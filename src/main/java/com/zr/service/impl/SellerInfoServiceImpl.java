package com.zr.service.impl;

import com.zr.dataobject.SellerInfo;
import com.zr.repository.SellerInfoRepository;
import com.zr.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 22:17
 * @Description:
 */
@Service
@Slf4j
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }


    @Override
    public SellerInfo findSellerInfoById(String id) {
        return sellerInfoRepository.findById(id);
    }
}
