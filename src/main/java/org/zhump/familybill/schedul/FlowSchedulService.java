package org.zhump.familybill.schedul;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zhump.familybill.module.ReportDay;
import org.zhump.familybill.service.ReportDayService;
import org.zhump.familybill.service.UserFlowService;
import org.zhump.familybill.util.JackJsonUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Title:FlowSchedulService
* Description:TODO 定时任务上报统计
* @author zhump
* @version 1.0.0
* @date 2021/6/13 11:22
*/
@Component
@Log4j2
public class FlowSchedulService {


    @Autowired
    private UserFlowService userFlowService;

    @Autowired
    private ReportDayService reportDayService;

    /**
     * 每天23点50分50秒开始统计
     */
    @Scheduled(cron = "58 59 23 * * ? ")
    public void dayStatisticsJob(){
        log.info("每天23点50分50秒开始统计当天的累计消费金额开始");
        //获取当前日志
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        //查询当天的汇总数据
        Map<String, Object> stringObjectMap = userFlowService.selectDayParams();
        if (stringObjectMap == null || stringObjectMap.size() <=0){
            log.info("定时任务按天通知 dayStatisticsJob() 未统计到数据");
            return;
        }
        BigDecimal price = new BigDecimal(stringObjectMap.get("price").toString());
        boolean insert = reportDayService.insert(price, format);
        if (!insert){
            log.info("定时任务按天通知 dayStatisticsJob() 统计数据异常");
            return;
        }
    }


}
