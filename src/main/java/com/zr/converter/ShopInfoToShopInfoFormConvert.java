package com.zr.converter;

import com.zr.dataobject.ShopInfo;
import com.zr.form.ShopInfoForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/29 19:38
 * @Description:
 */
public class ShopInfoToShopInfoFormConvert {
    public static ShopInfoForm convert(ShopInfo shopInfo){
        ShopInfoForm shopInfoForm = new ShopInfoForm();
        BeanUtils.copyProperties(shopInfo,shopInfoForm);
        return  shopInfoForm;
    }

    public static List<ShopInfoForm> convert(List<ShopInfo> shopInfoList){
        return  shopInfoList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
