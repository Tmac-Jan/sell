package com.zr.service;

import com.zr.dataobject.SupportInfo;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 16:35
 * @Description:
 */
public interface SupportInfoService {
    SupportInfo findByOpenid(String openid);
}
