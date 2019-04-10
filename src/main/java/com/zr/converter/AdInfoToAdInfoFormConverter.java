package com.zr.converter;

import com.zr.dataobject.AdInfo;
import com.zr.form.AdInfoForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 12:42
 * @Description:
 */
public class AdInfoToAdInfoFormConverter {
    public static AdInfoForm convert(AdInfo adInfo){
        AdInfoForm adInfoForm = new AdInfoForm();
        BeanUtils.copyProperties(adInfo,adInfoForm);
        return  adInfoForm;
    }
    public static List<AdInfoForm> convert(List<AdInfo> adInfoList){
        return  adInfoList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
