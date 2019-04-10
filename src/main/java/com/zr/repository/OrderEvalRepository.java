package com.zr.repository;

import com.zr.dataobject.OrderEval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:25
 * @Description:
 */
public interface OrderEvalRepository extends JpaRepository<OrderEval,String> {
    Page<OrderEval> findOrderEvalByBuyerId(String buyerId, Pageable pageable);
    Page<OrderEval> findOrderEvalByShopId(String shopId, Pageable pageable);
}
