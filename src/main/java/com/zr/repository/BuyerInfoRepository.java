package com.zr.repository;

import com.zr.dataobject.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 16:22
 * @Description:
 */
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo,Integer> {
    BuyerInfo findByOpenid(String openid);
}
