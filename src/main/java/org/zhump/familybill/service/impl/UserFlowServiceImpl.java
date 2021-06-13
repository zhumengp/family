package org.zhump.familybill.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhump.familybill.controller.response.UserFlowCateMontChartResponse;
import org.zhump.familybill.dao.UserFlowDao;
import org.zhump.familybill.module.Category;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.UserFlow;
import org.zhump.familybill.security.SecurityContextUtils;
import org.zhump.familybill.service.CategoryService;
import org.zhump.familybill.service.UserFlowService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.zhump.familybill.vo.CateMontChartDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流水服务实现类
 * @author zhump
 */
@Service
public class UserFlowServiceImpl implements UserFlowService {

    @Autowired
    private UserFlowDao userFlowDao;

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增
     * @param userId 用户ID
     * @param categoryId 分类ID
     * @param price 价格
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(long userId, long categoryId, BigDecimal price,String comsumeTime,String remark) {
        UserFlow userFlow = new UserFlow();
        userFlow.setUserId(userId);
        userFlow.setCategoryId(categoryId);
        userFlow.setPrice(price);
        userFlow.setComsumeTime(Timestamp.valueOf(comsumeTime));
        userFlow.setRemark(remark);
        int res = userFlowDao.insert(userFlow);
        return res >= 0 ? true : false;
    }

    /**
     * 分页查询
     * @param pageIndex  当前页
     * @param pageSize 当前页条数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param categoryId 分类ID
     * @return
     */
    @Override
    public PageInfo<List<UserFlow>> selectAll(Integer pageIndex,Integer pageSize,String startTime,String endTime,Long categoryId) {
        Page<UserFlow> page = PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map = new HashMap<>(16);
        if (StringUtils.isNotBlank(startTime)){
            map.put("startTime",startTime);
            map.put("endTime",endTime);
        }
        if (categoryId != null){
            map.put("categoryId",categoryId);
        }
        //用户Id
        long userId = SecurityContextUtils.getLoginUserinfo().getId();
        map.put("userId",userId);
        this.userFlowDao.selectAll(map);
        long total = page.getTotal();
        if(total <=0 ) {
        	PageInfo<List<UserFlow>> pageInfo = new PageInfo<List<UserFlow>>(pageIndex,pageSize,total,new ArrayList<>());
        	return pageInfo;
        }
        List<UserFlow> result = page.getResult();
        PageInfo<List<UserFlow>> pageInfo = new PageInfo<List<UserFlow>>(pageIndex,pageSize,total,result);
        return pageInfo;
    }

    /**
     * 按月统计
     * @return
     */
    @Override
    public PageInfo<List<Map<String,Object>>> selectMonth(Integer pageIndex,Integer pageSize) {
        Page<Map<String,Object>> page = PageHelper.startPage(pageIndex, pageSize);
        this.userFlowDao.selectMonth();
        long total = page.getTotal();
        if (total <= 0 ){
            PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo<List<Map<String,Object>>>(pageIndex,pageSize,total,new ArrayList<>());
            return pageInfo;
        }
        List<Map<String, Object>> result = page.getResult();
        PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo<List<Map<String,Object>>>(pageIndex,pageSize,total,result);
        return pageInfo;
    }


    /**
     * 按月统计
     * @return
     */
    @Override
    public List<Map<String,Object>> monthCahrt() {
        List<Map<String, Object>> maps = this.userFlowDao.selectMonth();
        return maps;
    }

    /**
     * 删除
     * @param id 主键
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(long id) {
        int delete = userFlowDao.delete(id);
        return delete >= 0  ? true :false;
    }

    /**
     * 编辑
     * @param userFlow 编辑参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UserFlow userFlow) {
        int update = userFlowDao.update(userFlow);
        return update >= 0  ? true :false;
    }

    /**
     * 按分类查询chart
     * @return
     */
    @Override
    public List<Map<String, Object>> categoryChart() {
        return userFlowDao.categoryChart();
    }

    /**
     * 折线图--每一个月份，top 10 的支出额度
     * @return
     */
    @Override
    public UserFlowCateMontChartResponse cateMontChart() {
        UserFlowCateMontChartResponse userFlowCateMontChartResponses = new UserFlowCateMontChartResponse();
        List<CateMontChartDTO> list = userFlowDao.cateMontChart(new HashMap<>(16));
        List<Map<String, Object>> maps1 = this.userFlowDao.selectMonth();
        List<Category> categories = categoryService.categoryId();
        //消费方式
        userFlowCateMontChartResponses.setCateNames(categories.stream().map(u->u.getRemark()).toArray(String[]::new));
        //月份
        userFlowCateMontChartResponses.setMonts(list.stream().map(u -> u.getMont()).toArray(String[]::new));
        List<UserFlowCateMontChartResponse.UserFlowCateMontChartResponseItem> rlist = new ArrayList<>();
        Set<Long> idSets = list.stream().map(u -> u.getCategoryId()).collect(Collectors.toSet());
        for(Long idSet : idSets){
            UserFlowCateMontChartResponse.UserFlowCateMontChartResponseItem userFlowCateMontChartResponse = new UserFlowCateMontChartResponse.UserFlowCateMontChartResponseItem();
            BigDecimal[] big = new BigDecimal[maps1.size()];
            Map<String, Object> cateMap = new HashMap<>(16);
            cateMap.put("categoryId",idSet);
            //查询月份
            List<CateMontChartDTO> cateMaps = userFlowDao.cateMontChart(cateMap);
            for(int i = 0; i < maps1.size(); i++){
                big[i] = new BigDecimal("0.00");
                String monts = maps1.get(i).get("mont").toString();
                userFlowCateMontChartResponse.setCateName(cateMaps.stream().map(u -> u.getMont()).filter(u -> u.equals(monts)).findFirst().orElse(""));
            }
            userFlowCateMontChartResponse.setPrice(big);
            rlist.add(userFlowCateMontChartResponse);
        }
        userFlowCateMontChartResponses.setChartData(rlist);
        userFlowCateMontChartResponses.setChartData(rlist);
        return userFlowCateMontChartResponses;
    }

    /**
     * 按年汇总
     * @return
     */
    @Override
    public List<Map<String, Object>> selectYears() {
        return userFlowDao.selectYears();
    }

    @Override
    public List<Map<String, Object>> selectDay() {
        return userFlowDao.selectDay();
    }

    @Override
    public Map<String, Object> selectDayParams(String date) {
        return userFlowDao.selectDayparams(date);
    }
}
