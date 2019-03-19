package com.zr.service;

import com.zr.dataobject.SellerInfo;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 22:14
 * @Description:
 */
public interface SellerInfoService {
    SellerInfo findSellerInfoById(String id);
    SellerInfo findSellerInfoByOpenid(String sellerId);
}
