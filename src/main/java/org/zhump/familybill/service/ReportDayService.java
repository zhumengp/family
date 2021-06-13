package org.zhump.familybill.service;

import java.math.BigDecimal;

/**
 * Title:ReportDayService
 * Description:TODO 描述一下这个类是干嘛的
 *
 * @author zhump
 * @version 1.0.0
 * @date
 */
public interface ReportDayService {

    /**
     * 新增
     * @param price
     * @param date
     * @return
     */
    boolean insert(BigDecimal price,String date);


    boolean test() throws Exception;
}
