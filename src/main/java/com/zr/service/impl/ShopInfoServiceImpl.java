package com.zr.service.impl;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.repository.SellerInfoRepository;
import com.zr.repository.ShopInfoRepository;
import com.zr.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 22:02
 * @Description:
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {
    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @Override
    public ShopInfo findBySellerInfo(SellerInfo sellerInfo) {
        return shopInfoRepository.findBySellerInfo(sellerInfo);
    }
}
