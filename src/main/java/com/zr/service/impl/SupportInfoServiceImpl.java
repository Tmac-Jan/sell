package com.zr.service.impl;

import com.zr.dataobject.SupportInfo;
import com.zr.repository.SupportInfoRepository;
import com.zr.service.SupportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 16:36
 * @Description:
 */
@Service
public class SupportInfoServiceImpl implements SupportInfoService {
    @Autowired
    private SupportInfoRepository supportInfoRepository;
    @Override
    public SupportInfo findByOpenid(String openid) {
        return supportInfoRepository.findByOpenid(openid);
    }
}
