package com.zr.service;

import com.zr.dataobject.ShopRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:26
 * @Description:
 */
public interface ShopRequestService {
    ShopRequest findById(String id);
    void agreeRequest(String id);
    Page<ShopRequest> findAllOrderByUpdateTimeDesc(Pageable pageable);

}
