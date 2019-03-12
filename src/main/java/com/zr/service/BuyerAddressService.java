package com.zr.service;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:30
 * @Description:
 */
public interface BuyerAddressService {
    List<BuyerAddress> findBuyerAddressByBuyerInfo(BuyerInfo buyerInfo);
}
