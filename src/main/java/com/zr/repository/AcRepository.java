package com.zr.repository;

import com.zr.dataobject.AcInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:48
 * @Description:
 */
public interface AcRepository extends JpaRepository<AcInfo,String> {
    Page<AcInfo> findAll(Pageable pageable);
}
