package org.zhump.familybill.service;


import org.apache.ibatis.annotations.Param;
import org.zhump.familybill.controller.response.UserFlowCateMontChartResponse;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.UserFlow;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 流水服务接口
 * @author zhump
 */
public interface UserFlowService {

  /**
   * TODO 描述一下这个方法是干嘛用的
   
   * @param userId 用户ID
   * @param categoryId 分类ID
   * @param price 价格
   * @param comsumeTime 消费时间
   * @param remark 备注
   * @author zhump
   * @return boolean
   * @date 2021/4/10 20:41
   * @throws
   */
    boolean insert(long userId,long categoryId, BigDecimal price,String comsumeTime,String remark);

    /**
     * 分页查询
     * @param pageIndex  当前页
     * @param pageSize 当前页条数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param categoryId 分类ID
     * @author zhump
     * @return
     */
    PageInfo<List<UserFlow>> selectAll(Integer pageIndex,Integer pageSize,String startTime,String endTime,Long categoryId);

    /**
     * 按月分页查询
     * @param pageIndex 当前页
     * @param pageSize  当前页条数
     * @author zhump
     * @return org.zhump.familybill.module.PageInfo<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     * @date 2021/4/10 20:42
     * @throws 
     */
    PageInfo<List<Map<String,Object>>> selectMonth(Integer pageIndex,Integer pageSize);

    /**
     * 按日期展示
     * @return
     */
    List<Map<String,Object>> monthCahrt();

    /**
     * 删除
     * @param id 主键
     * @author zhump
     * @return
     */
    boolean delete(long id);

    /**
     * 编辑
     * @param userFlow 编辑参数
     * @author zhump
     * @return
     */
    boolean update (UserFlow userFlow);


    /**
     * 按分类汇总
     * @return
     */
    List<Map<String,Object>> categoryChart();

    /**
     * 按月分类汇总
     * @return
     */
    UserFlowCateMontChartResponse cateMontChart();

    /**
     * 按年汇总
     * @return
     */
    List<Map<String,Object>> selectYears();

  /**
   * 按天汇总
   * @return
   */
  List<Map<String,Object>> selectDay();


  Map<String,Object> selectDayParams(String date);
}
