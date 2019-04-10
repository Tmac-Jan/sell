package com.zr.service;

import com.zr.dataobject.AcInfo;
import com.zr.form.AcInfoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:50
 * @Description:
 */
public interface AcService {
    List<AcInfo> findAllAcInfo();
    Page<AcInfo> findAll(Pageable pageable);
    Page<AcInfoForm> getAllToAcInfoForm(Pageable pageable);
    AcInfo findOne(String id);
    void save(AcInfo acInfo);
}
