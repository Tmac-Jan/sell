package com.zr.service.impl;

import com.zr.config.UpYunConfig;
import com.zr.converter.AdInfoToAdInfoFormConverter;
import com.zr.dataobject.AdInfo;
import com.zr.form.AdInfoForm;
import com.zr.repository.AdRepository;
import com.zr.service.AdService;
import com.zr.utils.ImageHostUtil;
import com.zr.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:52
 * @Description:
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UpYunConfig upYunConfig;
    @Override
    public List<AdInfo> findAllAdInfo() {
        return adRepository.findAll();
    }

    @Override
    public Page<AdInfo> findAll(Pageable pageable) {
        return adRepository.findAll(pageable);
    }

    @Override
    public Page<AdInfoForm> getAllToAdInfoForm(Pageable pageable){
          Page<AdInfo> adInfoPage = this.findAll(pageable);
          List<AdInfoForm> adInfoFormList = AdInfoToAdInfoFormConverter.convert(adInfoPage.getContent());
          return  new PageImpl<AdInfoForm>(adInfoFormList,pageable,adInfoPage.getTotalElements());
    }

    @Override
    public void save(AdInfo adInfo) {
        adInfo.setAdImage(ImageHostUtil.addImageHost(upYunConfig.getImageHost(),adInfo.getAdImage()));
        adRepository.save(adInfo);
    };

    @Override
    public AdInfo findOne(String id) {
        return adRepository.findOne(id);
    }
}
