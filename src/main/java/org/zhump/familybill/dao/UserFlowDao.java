package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zhump.familybill.module.UserFlow;
import org.zhump.familybill.vo.CateMontChartDTO;

import java.util.List;
import java.util.Map;

/**
 *@Title UserFlowDao
 *@Description: 用户流水mapper
 *
 *@author zhump
 *@date 2021/4/10 21:00
 */
@Mapper
public interface UserFlowDao {


    /**
     * 新增
     * @param userFlow
     * @return
     */
    int insert(UserFlow userFlow);

    /**
     * 查询所有
     * @param map
     * @return
     */
    List<UserFlow> selectAll(Map<String,Object> map);

    /**
     * 按月统计
     * @return
     */
    List<Map<String,Object>> selectMonth();

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 编辑
     * @param userFlow
     * @return
     */
    int update (UserFlow userFlow);

    /**
     * 按分类汇总
     * @return
     */
    List<Map<String,Object>> categoryChart();

    /**
     * 按月统计
     * @param map
     * @return
     */
    List<CateMontChartDTO> cateMontChart(Map<String,Object> map);

    /**
     * 按年统计
     * @return
     */
    List<Map<String,Object>> selectYears();

    /**
     * 按天统计-汇总上报
     * @return
     */
    List<Map<String,Object>> selectDay();

    /**
     * 按天统计-汇总上报
     * @return
     */
    Map<String,Object> selectDayparams(@Param("startTime")String startTime,@Param("endTime")String endTime);


}
