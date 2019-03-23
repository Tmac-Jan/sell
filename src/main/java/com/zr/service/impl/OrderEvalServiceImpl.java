package com.zr.service.impl;

import com.zr.dataobject.OrderEval;
import com.zr.repository.OrderEvalRepository;
import com.zr.service.OrderEvalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:48
 * @Description:
 */
@Service
public class OrderEvalServiceImpl implements OrderEvalService {
    @Autowired
    private OrderEvalRepository orderEvalRepository;
    @Override
    public Page<OrderEval> findOrderEvalByBuyerId(String buyerId, Pageable pageable) {
        return orderEvalRepository.findOrderEvalByBuyerId(buyerId,pageable);
    }

    @Override
    public Page<OrderEval> findOrderEvalByShopId(String shopId, Pageable pageable) {
        return orderEvalRepository.findOrderEvalByShopId(shopId,pageable);
    }
}
