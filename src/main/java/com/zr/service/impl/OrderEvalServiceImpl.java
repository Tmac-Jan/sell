package com.zr.service.impl;

import com.zr.converter.OrderEvalToOrderEvalFormConverter;
import com.zr.dataobject.OrderEval;
import com.zr.form.OrderEvalForm;
import com.zr.repository.OrderEvalRepository;
import com.zr.service.OrderEvalService;
import com.zr.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
        return orderEvalRepository.findOrderEvalByBuyerId(buyerId, pageable);
    }

    @Override
    public Page<OrderEval> findOrderEvalByShopId(String shopId, Pageable pageable) {
        return orderEvalRepository.findOrderEvalByShopId(shopId, pageable);
    }

    @Override
    public OrderEval findOne(String id) {
        return orderEvalRepository.findOne(id);
    }

    @Override
    public Page<OrderEvalForm> getOrderEvalFormByShopId(String shopId, Pageable pageable) {
        Page<OrderEval> orderEvalPage = this.findOrderEvalByShopId(shopId, pageable);
        List<OrderEvalForm> orderEvalFormList = OrderEvalToOrderEvalFormConverter.convert(orderEvalPage.getContent());
        return new PageImpl<OrderEvalForm>(orderEvalFormList, pageable, orderEvalPage.getTotalElements());
    }

    @Override
    public void save(OrderEval orderEval) {
        if (StringUtils.isEmpty(orderEval.getId()))
            orderEval.setId(RandomUtil.genUniqueKey());
        orderEvalRepository.save(orderEval);
    }

    @Override
    public void delete(OrderEval orderEval) {
        orderEvalRepository.delete(orderEval);
    }

    @Override
    public OrderEval findByBuyerOpenidAndOrderId(String buyerOpenid, String orderId) {
        return orderEvalRepository.findByBuyerOpenidAndOrderId(buyerOpenid,orderId);
    }
}
