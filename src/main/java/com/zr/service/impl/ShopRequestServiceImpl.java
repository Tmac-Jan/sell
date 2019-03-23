package com.zr.service.impl;

import com.zr.dataobject.ShopRequest;
import com.zr.repository.ShopRequestRepository;
import com.zr.service.ShopRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:55
 * @Description:
 */
@Service
public class ShopRequestServiceImpl implements ShopRequestService {

    @Autowired
    private ShopRequestRepository shopRequestRepository;
    @Override
    public ShopRequest findById(String id) {
       return shopRequestRepository.findById(id);
    }

    @Override
    public void agreeRequest(String id) {

    }

    @Override
    public Page<ShopRequest> findAllOrderByUpdateTimeDesc(Pageable pageable) {
        return shopRequestRepository.findAllOrderByUpdateTimeDesc(pageable);
    }
}
