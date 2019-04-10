package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/28 10:40
 * @Description: 广告实体，support管理
 */
@Entity
@Data
@DynamicUpdate
public class AdInfo {
    @Id
    private String id;
    private String adName;
    private String adImage;
    private String modifierOpenid;
    private String modifierName;
    private Date createTime;
    private Date updateTime;

}
