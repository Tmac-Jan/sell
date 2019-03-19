package com.zr.repository;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 17:15
 * @Description:
 */
public interface ShopInfoRepository extends JpaRepository<ShopInfo,String> {
    List<ShopInfo> findByShopType(String shopType);
    ShopInfo findBySellerInfo(SellerInfo sellerInfo);
}
