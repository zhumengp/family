package org.zhump.familybill.module;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
* Title:ReportDay
* Description:数据上报统计实体类-天
* @author zhump
* @version 1.0.0
* @date 2021/6/13 21:14
*/
@Data
public class ReportDayBuilder {

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 上报日期
     */
    private Date reportDate;

    public ReportDayBuilder() {

    }

    public ReportDayBuilder price(BigDecimal price){
        this.price = price;
        return this;
    }

    public ReportDayBuilder reportDate(Date reportDate){
        this.reportDate = reportDate;
        return this;
    }


    public ReportDay builder(){
        return new ReportDay(this.price = price,this.reportDate = reportDate);
    }


}
