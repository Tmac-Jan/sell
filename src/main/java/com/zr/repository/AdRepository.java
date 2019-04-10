package com.zr.repository;

import com.zr.dataobject.AdInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:48
 * @Description:
 */
public interface AdRepository extends JpaRepository<AdInfo,String> {
    Page<AdInfo> findAll(Pageable pageable);
}
