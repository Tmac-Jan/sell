package com.zr.service.impl;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.enums.ShopStatusEnum;
import com.zr.repository.SellerInfoRepository;
import com.zr.repository.ShopInfoRepository;
import com.zr.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ShopInfo findOne(String id) {
        return shopInfoRepository.findById(id);
    }

    @Override
    public void save(ShopInfo shopInfo) {
        shopInfoRepository.save(shopInfo);
    }

    @Override
    public Page<ShopInfo> findAll(Pageable pageable) {
        return shopInfoRepository.findAll(pageable);
    }

    @Override
    public void block(ShopInfo shopInfo) {
        shopInfo.setShopBlock(ShopStatusEnum.SHOP_BLOCK.getStatus());
        shopInfoRepository.save(shopInfo);
    }

    @Override
    public void reOpen(ShopInfo shopInfo) {
        shopInfo.setShopBlock(ShopStatusEnum.SHOP_OK.getStatus());
        shopInfoRepository.save(shopInfo);
    }
}
