package com.zr.converter;

import com.zr.dataobject.BuyerAddress;
import com.zr.form.BuyerAddressForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/4/10 21:46
 * @Description:
 */
public class BuyerAddressToBuyerAddressFormConverter {
    public static BuyerAddressForm convert(BuyerAddress buyerAddress){
        BuyerAddressForm buyerAddressForm = new BuyerAddressForm();
        BeanUtils.copyProperties(buyerAddress,buyerAddressForm);
        return buyerAddressForm;
    }
    public static List<BuyerAddressForm> convert(List<BuyerAddress> buyerAddressList){
        return buyerAddressList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
