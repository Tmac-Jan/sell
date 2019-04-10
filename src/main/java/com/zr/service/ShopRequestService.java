package com.zr.service;

import com.zr.dataobject.ShopRequest;
import com.zr.form.ShopRequestForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:26
 * @Description:
 */
public interface ShopRequestService {
    ShopRequest findOne(String id);
    void agreeRequest(ShopRequest shopRequest);
    void handleRequest(ShopRequest shopRequest);
    void rejectRequest(ShopRequest shopRequest);
    void cancelRequest(ShopRequest shopRequest);
    void save(ShopRequest shopRequest);
    Page<ShopRequest> findAllOrderByUpdateTimeDesc(Pageable pageable);
    Page<ShopRequest> findAllBySellerOpenid(String openid,Pageable pageable);
    Page<ShopRequestForm> getAllBySellerOpenid(String openid,Pageable pageable);
}
