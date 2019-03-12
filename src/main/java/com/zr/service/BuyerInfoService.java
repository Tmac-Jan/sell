package com.zr.service;

import com.zr.dataobject.BuyerInfo;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:26
 * @Description:
 */
public interface BuyerInfoService {
    BuyerInfo findBuyerInfoByOpenid(String openid);
    BuyerInfo save(BuyerInfo buyerInfo);
}
