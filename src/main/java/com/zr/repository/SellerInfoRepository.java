package com.zr.repository;

import com.zr.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/19 22:12
 * @Description:
 */

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findById(String id);
    SellerInfo findByOpenid(String openId);
}
