package com.zr.converter;

import com.zr.dataobject.ShopRequest;
import com.zr.form.ShopRequestForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 22:37
 * @Description:
 */
public class ShopRequestFormConverter {
    public static ShopRequestForm convert(ShopRequest shopRequest){
        ShopRequestForm shopRequestForm = new ShopRequestForm();
        BeanUtils.copyProperties(shopRequest,shopRequestForm);
        return  shopRequestForm;
    }
    public static List<ShopRequestForm> convert(List<ShopRequest> shopRequestList){
        return  shopRequestList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
