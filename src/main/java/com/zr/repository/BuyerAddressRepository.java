package com.zr.repository;

import com.zr.dataobject.BuyerAddress;
import com.zr.dataobject.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:19
 * @Description:
 */
public interface BuyerAddressRepository extends JpaRepository<BuyerAddress, Integer> {
    List<BuyerAddress> findByBuyerInfo(BuyerInfo buyerInfo);
}
