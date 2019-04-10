package com.zr.service;

import com.zr.dataobject.BuyerComplain;
import com.zr.form.BuyerComplainForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:13
 * @Description:
 */
public interface BuyerComplainService {
    Page<BuyerComplain> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    Page<BuyerComplain> findByBuyerShopId(String shopId, Pageable pageable);
    BuyerComplain findOne(String id);
    Page<BuyerComplainForm> getBuyerComplainFormByShopId(String shopId, Pageable pageable);
    Page<BuyerComplain> findAll(Pageable pageable);
    Page<BuyerComplainForm> getAll(Pageable pageable);
    void save(BuyerComplain buyerComplain);
}
