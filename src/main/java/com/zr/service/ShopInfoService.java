package com.zr.service;

import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 17:14
 * @Description:
 */
public interface ShopInfoService {
    ShopInfo findBySellerInfo(SellerInfo sellerInfo);
}
