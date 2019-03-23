package com.zr.service;

import com.zr.dataobject.OrderEval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:27
 * @Description:
 */
public interface OrderEvalService {
    Page<OrderEval> findOrderEvalByBuyerId(String buyerId, Pageable pageable);
    Page<OrderEval> findOrderEvalByShopId(String shopId, Pageable pageable);
}
