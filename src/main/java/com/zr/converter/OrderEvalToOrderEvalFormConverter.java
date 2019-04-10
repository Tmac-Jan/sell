package com.zr.converter;

import com.zr.dataobject.OrderEval;
import com.zr.form.OrderEvalForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/24 12:11
 * @Description:
 */
public class OrderEvalToOrderEvalFormConverter {
    public static OrderEvalForm convert(OrderEval orderEval){
        OrderEvalForm orderEvalForm = new OrderEvalForm();
        BeanUtils.copyProperties(orderEval,orderEvalForm);
        return  orderEvalForm;
    }
    //流是一次性的
    public static List<OrderEvalForm> convert(List<OrderEval> orderEvalList){
        return  orderEvalList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
