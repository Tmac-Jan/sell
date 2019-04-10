package com.zr.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Auther: Administrator (zhangrun macmanboy@foxmail.com)
 * @Date: 2019/3/20 15:07
 * @Description:
 */
@Data
public class ProductForm {
    private String productId;

    /** 名字. */
    @NotEmpty(message = "商品名字不能为空")
    private String productName;

    /** 单价. */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal productPrice;

    /** 库存. */
    @NotNull(message = "库存不能为空")
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    @NotEmpty(message = "商品小图不能为空")
    private String productIcon;

    /** 类目编号. */
    @NotEmpty(message = "商品类目编号不能为空")
    private String categoryType;
}
