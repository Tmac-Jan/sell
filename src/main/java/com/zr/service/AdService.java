package com.zr.service;

import com.zr.dataobject.AdInfo;
import com.zr.form.AdInfoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:51
 * @Description:
 */
public interface AdService {
   List<AdInfo> findAllAdInfo();
   Page<AdInfo> findAll(Pageable pageable);
   Page<AdInfoForm> getAllToAdInfoForm(Pageable pageable);
   void save(AdInfo adInfo);
   AdInfo findOne(String id);
}
