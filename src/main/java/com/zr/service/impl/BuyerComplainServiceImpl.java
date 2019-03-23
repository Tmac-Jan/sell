package com.zr.service.impl;

import com.zr.converter.BComplainToBComplainFormConverter;
import com.zr.dataobject.BuyerComplain;
import com.zr.form.BuyerComplainForm;
import com.zr.repository.BuyerComplainRepository;
import com.zr.service.BuyerComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/22 20:50
 * @Description:
 */
@Service
public class BuyerComplainServiceImpl implements BuyerComplainService {
    @Autowired
    private BuyerComplainRepository buyerComplainRepository;
    @Override
    public Page<BuyerComplain> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        return buyerComplainRepository.findByBuyerOpenid(buyerOpenid,pageable);
    }

    @Override
    public Page<BuyerComplain> findByBuyerShopId(String shopId, Pageable pageable) {
        return buyerComplainRepository.findByShopId(shopId,pageable);
    }

    @Override
    public BuyerComplain findOne(String id) {
        return buyerComplainRepository.findOne(id);
    }
   public Page<BuyerComplainForm> getBuyerComplainFormByShopId(String shopId, Pageable pageable){
        Page<BuyerComplain> buyerComplainPage = this.findByBuyerShopId(shopId,pageable);
       List<BuyerComplainForm>  buyerComplainFormList = BComplainToBComplainFormConverter.convert(buyerComplainPage.getContent());
       return new PageImpl<BuyerComplainForm>(buyerComplainFormList,pageable,buyerComplainPage.getTotalElements());
   }

    @Override
    public void save(BuyerComplain buyerComplain) {
        buyerComplainRepository.save(buyerComplain);
    }
}
