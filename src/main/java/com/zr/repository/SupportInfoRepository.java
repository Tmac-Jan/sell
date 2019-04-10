package com.zr.repository;

import com.zr.dataobject.SupportInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 16:33
 * @Description:
 */
public interface SupportInfoRepository extends JpaRepository<SupportInfo,String> {
    SupportInfo findByOpenid(String openid);
}
