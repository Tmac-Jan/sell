package com.zr.converter;

import com.zr.dataobject.AcInfo;
import com.zr.form.AcInfoForm;
import com.zr.form.AdInfoForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 12:25
 * @Description:
 */
public class AcInfoToAcInfoFormConverter {
    public static AcInfoForm convert(AcInfo acInfo){
        AcInfoForm acInfoForm = new AcInfoForm();
        BeanUtils.copyProperties(acInfo,acInfoForm);
        return  acInfoForm;
    }
    public static List<AcInfoForm> convert(List<AcInfo> acInfoList){
        return  acInfoList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
