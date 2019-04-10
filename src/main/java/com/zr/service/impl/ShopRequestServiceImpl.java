package com.zr.service.impl;

import com.zr.config.UpYunConfig;
import com.zr.converter.ShopRequestFormConverter;
import com.zr.dataobject.SellerInfo;
import com.zr.dataobject.ShopInfo;
import com.zr.dataobject.ShopRequest;
import com.zr.enums.ShopRequestResultEnum;
import com.zr.enums.ShopStatusEnum;
import com.zr.form.ShopRequestForm;
import com.zr.repository.SellerInfoRepository;
import com.zr.repository.ShopInfoRepository;
import com.zr.repository.ShopRequestRepository;
import com.zr.service.ShopRequestService;
import com.zr.utils.ImageHostUtil;
import com.zr.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/21 22:55
 * @Description:
 */
@Service
public class ShopRequestServiceImpl implements ShopRequestService {

    @Autowired
    private ShopRequestRepository shopRequestRepository;
    @Autowired
    private ShopInfoRepository shopInfoRepository;
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Autowired
    private UpYunConfig upYunConfig;
    @Override
    public ShopRequest findOne(String id) {
       return shopRequestRepository.findOne(id);
    }

    @Override
    public void agreeRequest(ShopRequest shopRequest) {
          shopRequest.setRequestResult(ShopRequestResultEnum.REQUEST_AGREE.getStatus());
          shopRequestRepository.save(shopRequest);
          SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(shopRequest.getSellerOpenid());
          ShopInfo shopInfo = shopInfoRepository.findBySellerInfo(sellerInfo);
          if (shopInfo==null){
              shopInfo = new ShopInfo();
              //店铺编号
              shopInfo.setId(RandomUtil.genUniqueKey());
              shopInfo.setShopAddress(shopRequest.getShopAddress());
              shopInfo.setShopPhone(shopRequest.getShopPhone());
              shopInfo.setShopBlock(ShopStatusEnum.SHOP_OK.getStatus());
              shopInfo.setShopFile(shopRequest.getLicenseImg());
          }else{//解锁编号
              shopInfo.setShopBlock(ShopStatusEnum.SHOP_OK.getStatus());
          }
        shopInfoRepository.save(shopInfo);
    }

    @Override
    public void handleRequest(ShopRequest shopRequest) {
        shopRequest.setRequestResult(ShopRequestResultEnum.REQUEST_HANDLE.getStatus());
        shopRequestRepository.save(shopRequest);
    }

    @Override
    public void rejectRequest(ShopRequest shopRequest) {
       shopRequest.setRequestResult(ShopRequestResultEnum.REQUEST_REFECT.getStatus());
       shopRequestRepository.save(shopRequest);
    }

    @Override
    public void cancelRequest(ShopRequest shopRequest) {
        shopRequest.setRequestResult(ShopRequestResultEnum.REQUEST_CANCEL.getStatus());
        shopRequestRepository.save(shopRequest);
    }

    @Override
    public void save(ShopRequest shopRequest) {
        if (!StringUtils.isEmpty(shopRequest.getRequestImg())){
            shopRequest.setRequestImg(ImageHostUtil.addImageHost(
                    upYunConfig.getImageHost(),shopRequest.getRequestImg()));
        }
        shopRequest.setLicenseImg(ImageHostUtil.addImageHost(
                upYunConfig.getImageHost(),shopRequest.getLicenseImg()));
        shopRequest.setHealthImg(ImageHostUtil.addImageHost(
                upYunConfig.getImageHost(),shopRequest.getHealthImg()));
        shopRequestRepository.save(shopRequest);
    }

    @Override
    public Page<ShopRequest> findAllOrderByUpdateTimeDesc(Pageable pageable) {
        return shopRequestRepository.findAllOrderByUpdateTimeDesc(pageable);
    }

    @Override
    public Page<ShopRequest> findAllBySellerOpenid(String openid, Pageable pageable) {
        return shopRequestRepository.findAllBySellerOpenid(openid,pageable);
    }

    @Override
    public Page<ShopRequestForm> getAllBySellerOpenid(String openid, Pageable pageable) {
        Page<ShopRequest> shopRequestPage = this.findAllBySellerOpenid(openid,pageable);
        List<ShopRequestForm> shopRequestFormList = ShopRequestFormConverter.convert(shopRequestPage.getContent());
        return new PageImpl<ShopRequestForm>(shopRequestFormList,pageable,shopRequestPage.getTotalElements());
    }
}
