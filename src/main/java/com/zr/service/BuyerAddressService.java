package com.zr.service;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import com.zr.form.BuyerAddressForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:30
 * @Description:
 */
public interface BuyerAddressService {
    Page<BuyerAddressForm> findBuyerAddressByBuyerInfo(BuyerInfo buyerInfo,Pageable pageable);
    BuyerAddress findBuyerAddressByIdAndOpenid(String id,String openId);
    BuyerAddress save(BuyerAddress buyerAddress);
    void delete(String id);
    Page<BuyerAddressForm> findAll(Pageable pageable);
    List<BuyerAddressForm> findAllByBuyerInfo(BuyerInfo buyerInfo);
}
