package com.zr.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.zr.dto.OrderDTO;
import com.zr.service.OrderService;
import com.zr.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/14 11:02
 * @Description:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public PayResponse notify(String notifyData) {
        return null;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        return null;
    }
}
