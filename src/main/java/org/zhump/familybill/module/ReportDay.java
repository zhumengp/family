package org.zhump.familybill.module;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.function.BiConsumer;

/**
* Title:ReportDay
* Description:数据上报统计实体类-天
* @author zhump
* @version 1.0.0
* @date 2021/6/13 21:14
*/
@Data
public class ReportDay {

    /**
     * 主键
     */
    private Long id;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 上报日期
     */
    private Date reportDate;

    public ReportDay(BigDecimal price, Date reportDate) {
        this.price = price;
        this.reportDate = reportDate;
    }
}
