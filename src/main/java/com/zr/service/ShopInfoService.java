package com.zr.service;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 17:14
 * @Description:
 */
public interface ShopInfoService {
    ShopInfo findBySellerInfo(SellerInfo sellerInfo);
    ShopInfo findOne(String id);
    void save(ShopInfo shopInfo);
    Page<ShopInfo> findAll(Pageable pageable);
    void block(ShopInfo shopInfo);
    void reOpen(ShopInfo shopInfo);
}
