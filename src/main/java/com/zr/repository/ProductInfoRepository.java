package com.zr.repository;

import com.zr.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 15:17
 * @Description:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);

    @Query(value = "select * from product_info as p where p.category_type in (select pc.category_type from product_category as pc where shop_id=?1) ORDER BY ?#{#pageable} ",nativeQuery = true)
    Page<ProductInfo> findAllByShopId(String shopId, Pageable pageable);
}
