package org.zhump.familybill.controller.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhmp
 * @description 说明一下这个类是干嘛的
 * @date $ $
 */
@Data
public class UserFlowCateMontChartResponse {

    /**
     * 分类名称
     */
    private String[] cateNames;

    /**
     * 月份
     */
    private String[] monts;

    private List<UserFlowCateMontChartResponseItem> chartData;

    @Data
    public static class UserFlowCateMontChartResponseItem{
        /**
         * 分类名称
         */
        private String cateName;
        /**
         * 金额
         */
        private BigDecimal[] price;
    }





}
