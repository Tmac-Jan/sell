package com.zr.service.impl;

import com.zr.config.UpYunConfig;
import com.zr.converter.AcInfoToAcInfoFormConverter;
import com.zr.dataobject.AcInfo;
import com.zr.form.AcInfoForm;
import com.zr.repository.AcRepository;
import com.zr.service.AcService;
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
public class AcServiceImpl implements AcService {
    @Autowired
    private AcRepository acRepository;
    @Autowired
    private UpYunConfig upYunConfig;
    @Override
    public List<AcInfo> findAllAcInfo() {
        return acRepository.findAll();
    }

    @Override
    public Page<AcInfo> findAll(Pageable pageable) {
        return acRepository.findAll(pageable);
    }

    @Override
    public Page<AcInfoForm> getAllToAcInfoForm(Pageable pageable){
        Page<AcInfo> acInfoPage = this.findAll(pageable);
        acInfoPage.getContent().stream().forEach(e->
                e.setAcImage(ImageHostUtil.addImageHost(upYunConfig.getImageHost(),e.getAcImage())));
        List<AcInfoForm> acInfoFormList = AcInfoToAcInfoFormConverter.convert(acInfoPage.getContent());
        return  new PageImpl<AcInfoForm>(acInfoFormList,pageable,acInfoPage.getTotalElements());
    }

    @Override
    public AcInfo findOne(String id) {
        AcInfo acInfo =  acRepository.findOne(id);
        if(acInfo !=null){
            acInfo.setAcImage(ImageHostUtil.addImageHost(upYunConfig.getImageHost(),acInfo.getAcImage()));
        }
        return acInfo;
    }

    @Override
    public void save(AcInfo acInfo) {
        acInfo.setAcImage(ImageHostUtil.addImageHost(upYunConfig.getImageHost(),acInfo.getAcImage()));
        acRepository.save(acInfo);
    }
}
