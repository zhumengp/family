package org.zhump.familybill.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * TODO 描述这个类是干嘛用的
 *
 * @author zhump
 * @date 2021/5/18 21:04
 **/
@Data
public class CateMontChartDTO {

    /**
     * 备注
     */
    private String remark;

    /**
     * 时间
     */
    private String mont;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 分类ID
     */
    private Long categoryId;

}
