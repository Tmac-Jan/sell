package com.zr.repository;

import com.zr.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 21:47
 * @Description:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>, JpaSpecificationExecutor<OrderMaster> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    Page<OrderMaster> findByShopId(String shopId,Pageable pageable);
}
