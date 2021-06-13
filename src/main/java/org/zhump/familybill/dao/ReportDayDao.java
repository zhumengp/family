package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zhump.familybill.module.ReportDay;
import org.zhump.familybill.module.UserFlow;
import org.zhump.familybill.vo.CateMontChartDTO;

import java.util.List;
import java.util.Map;

/**
 *@Title UserFlowDao
 *@Description: 按天统计数量
 *
 *@author zhump
 *@date 2021/4/10 21:00
 */
@Mapper
public interface ReportDayDao {


    /**
     * 新增
     * @param reportDay
     * @return
     */
    int insert(ReportDay reportDay);

}
