package com.zr.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/12 10:50
 * @Description:
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private String categoryType;

    private Integer shopId;

    private Date createTime;

    private Date updateTime;
}
