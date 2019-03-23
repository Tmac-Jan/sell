package com.zr.repository;

import com.zr.dataobject.ShopRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:27
 * @Description:
 */
public interface ShopRequestRepository  extends JpaRepository<ShopRequest,String> {
    ShopRequest findById(String id);
    @Query(value = "SELECT * from shop_request ORDER BY ?#{#pageable},update_time DESC " ,nativeQuery = true)
    Page<ShopRequest> findAllOrderByUpdateTimeDesc(Pageable pageable);
}