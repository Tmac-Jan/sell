package com.zr.converter;

import com.zr.dataobject.BuyerComplain;
import com.zr.form.BuyerComplainForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/23 12:01
 * @Description:
 */
public class BComplainToBComplainFormConverter {
    public static BuyerComplainForm convert(BuyerComplain buyerComplain){
        BuyerComplainForm buyerComplainForm = new BuyerComplainForm();
        BeanUtils.copyProperties(buyerComplain,buyerComplainForm);
        return  buyerComplainForm;
    }

    //流是一次性的
    public static List<BuyerComplainForm> convert(List<BuyerComplain> buyerComplainList){
        return  buyerComplainList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
