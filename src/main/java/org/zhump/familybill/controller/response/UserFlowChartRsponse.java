package org.zhump.familybill.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 *@Title UserFlowChartRsponse
 *@Description: 用户流水报表图响应类
 *
 *@author zhump
 *@date 2021/4/11 21:54
 */
public class UserFlowChartRsponse {

    private String date;

    private String price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
