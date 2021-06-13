package org.zhump.familybill.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.dao.ReportDayDao;
import org.zhump.familybill.module.ReportDay;
import org.zhump.familybill.module.ReportDayBuilder;
import org.zhump.familybill.service.ReportDayService;
import org.zhump.familybill.service.UserFlowService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title:ReportDayServiceImpl
 * Description:TODO 描述一下这个类是干嘛的
 *
 * @author zhump
 * @version 1.0.0
 * @date
 */
@Service
@Log4j2
public class ReportDayServiceImpl implements ReportDayService {



    @Autowired
    private UserFlowService userFlowService;

    @Autowired
    private ReportDayDao reportDayDao;

    @Override
    public boolean insert(BigDecimal price, String nowDate) {
        Date date = null;
        try {
            date = DateUtils.parseDate(nowDate,"yyyy-MM-dd");
        }catch (Exception e){
            log.info("时间转换异常",e);
        }
        ReportDay builder = new ReportDayBuilder().price(price).reportDate(date).builder();
        int insert = reportDayDao.insert(builder);
        return insert > 0 ? true : false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean test() throws Exception{
        List<Map<String, Object>> maps = userFlowService.selectDay();
        for(Map<String, Object> map : maps){
            BigDecimal price = new BigDecimal(map.get("price").toString());
            String day_time = map.get("day_time").toString();
            boolean insert = this.insert(price, day_time);
            if (!insert){
                throw new BusinessException("统计数据异常");
            }
        }
        return true;
    }
}
