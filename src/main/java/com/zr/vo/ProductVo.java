package com.zr.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/13 16:40
 * @Description:
 */
@Data
public class ProductVo implements Serializable {
    private static final long serialVersionUID = 5585749034096419420L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private String categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVOList;
}
