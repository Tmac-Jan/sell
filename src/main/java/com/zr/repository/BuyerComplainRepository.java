package com.zr.repository;

import com.zr.dataobject.BuyerComplain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:07
 * @Description:
 */
public interface BuyerComplainRepository extends JpaRepository<BuyerComplain,String> {
    Page<BuyerComplain> findAll(Pageable pageable);
    Page<BuyerComplain> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
    Page<BuyerComplain> findByShopId(String shopId,Pageable pageable);
    BuyerComplain findByBuyerOpenidAndOrderId(String buyerOpenid,String orderId);
}
