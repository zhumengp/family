package org.zhump.familybill.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhump.familybill.contants.Constants;
import org.zhump.familybill.contants.ResultWrap;
import org.zhump.familybill.controller.response.UserFlowCateMontChartResponse;
import org.zhump.familybill.controller.response.UserFlowChartRsponse;
import org.zhump.familybill.controller.response.UserFlowListRsponse;
import org.zhump.familybill.module.Category;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.User;
import org.zhump.familybill.module.UserFlow;
import org.zhump.familybill.service.CategoryService;
import org.zhump.familybill.service.UserFlowService;
import org.zhump.familybill.service.UserService;

/**
 *@Title UserFlowController
 *@Description: 用户流水控制层
 *
 *@author zhump
 *@date 2021/4/10 21:00
 */
@Controller
@RequestMapping(value = "/userFlow")
public class UserFlowController {


    @Autowired
    private UserFlowService userFlowService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * 新增
     * @param userId   用户id
     * @param categoryId 分类ID
     * @param price 价格
     * @param comsumeTime 消费时间
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResultWrap insert(@RequestParam(value = "userId",required = true) Long userId,
                            @RequestParam(value = "categoryId",required = true) Long categoryId,
                            @RequestParam(value = "price",required = true) BigDecimal price,
                            @RequestParam(value = "comsumeTime",required = true) String comsumeTime,
                            @RequestParam(value = "remark",required = true) String remark){
        userFlowService.insert(userId,categoryId,price,comsumeTime,remark);

        return  new ResultWrap(Constants.Status.SUCCESS,"true");
    }

    /**
     * 分页
     * @param pageIndex 当前页
     * @param pageSize 当前页条数
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param categoryId 分类ID
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public ResultWrap<PageInfo<List<UserFlowListRsponse>>> list(
    		@RequestParam(value="pageIndex",required= true,defaultValue = "1")Integer pageIndex,
    		@RequestParam(value="pageSize",required= true,defaultValue = "10")Integer pageSize,
            String startTime,
            String endTime,
            Long categoryId){
       PageInfo<List<UserFlow>> pageInfo = userFlowService.selectAll(pageIndex,pageSize,startTime,endTime,categoryId);
        if (pageInfo == null){
        	PageInfo<List<UserFlowListRsponse>> resutl = new PageInfo<List<UserFlowListRsponse>>(pageIndex,pageSize,0,new ArrayList<>());
        	return new ResultWrap<PageInfo<List<UserFlowListRsponse>>>(Constants.Status.SUCCESS,resutl);
        }
        List<UserFlow> userFlows = pageInfo.getData();
        List<UserFlowListRsponse> list = new ArrayList<>(userFlows.size());

        try {
            for (UserFlow userFlow :userFlows){
                UserFlowListRsponse userFlowListRsponse = new UserFlowListRsponse();
                userFlowListRsponse.setId(userFlow.getId());
                userFlowListRsponse.setCreateTime(userFlow.getCreateTime());
                userFlowListRsponse.setUpdateTime(userFlow.getUpdateTime());
                userFlowListRsponse.setRemark(userFlow.getRemark());
                Date comsumeTime = userFlow.getComsumeTime();
                String format = DateFormatUtils.format(comsumeTime, "yyyy-MM-dd HH:mm:ss");
                userFlowListRsponse.setComsumeTime(format);
                Category category = categoryService.getById(userFlow.getCategoryId());
                userFlowListRsponse.setCategoryName(category.getRemark());
                User user = userService.getByid(userFlow.getUserId());
                userFlowListRsponse.setUserName(user.getUserName());
                userFlowListRsponse.setUserId(user.getId());
                userFlowListRsponse.setCategoryId(category.getId());
                userFlowListRsponse.setPrice(userFlow.getPrice());
                list.add(userFlowListRsponse);
            }
            PageInfo<List<UserFlowListRsponse>> resutl = new PageInfo<List<UserFlowListRsponse>>(pageIndex,pageSize,pageInfo.getTotalCount(),list);
            return new ResultWrap<PageInfo<List<UserFlowListRsponse>>>(Constants.Status.SUCCESS,resutl);
        }catch (Exception e){
            return new ResultWrap<PageInfo<List<UserFlowListRsponse>>>(Constants.Status.Error);
        }

    }

    /**
     * 报表
     * @return
     */
    @RequestMapping(value = "/chart",method = RequestMethod.GET)
    @ResponseBody
    public ResultWrap<PageInfo<List<UserFlowChartRsponse>>> chart(@RequestParam(value="pageIndex",required= true,defaultValue = "1")Integer pageIndex,
                                                        @RequestParam(value="pageSize",required= true,defaultValue = "10")Integer pageSize){
        PageInfo<List<Map<String, Object>>> pageInfo = userFlowService.selectMonth(pageIndex, pageSize);
        if (pageInfo == null){
            PageInfo<List<UserFlowChartRsponse>> resutl = new PageInfo<List<UserFlowChartRsponse>>(pageIndex,pageSize,pageInfo.getTotalCount(),Collections.EMPTY_LIST);
            return new ResultWrap<PageInfo<List<UserFlowChartRsponse>>>(Constants.Status.SUCCESS,resutl);
        }
        List<Map<String, Object>> data = pageInfo.getData();
        List<UserFlowChartRsponse> rlist = data.stream().map(map -> {
            UserFlowChartRsponse userFlowChartRsponse = new UserFlowChartRsponse();
            String date = map.get("mont").toString();
            String price = map.get("price").toString();
            Timestamp createTime = Timestamp.valueOf(map.get("createTime").toString());
            Timestamp updateTime = Timestamp.valueOf(map.get("updateTime").toString());
            userFlowChartRsponse.setDate(date);
            userFlowChartRsponse.setPrice(price);
            userFlowChartRsponse.setCreateTime(createTime);
            userFlowChartRsponse.setUpdateTime(updateTime);
            return userFlowChartRsponse;

        }).collect(Collectors.toList());
        PageInfo<List<UserFlowChartRsponse>> resutl = new PageInfo<List<UserFlowChartRsponse>>(pageIndex,pageSize,pageInfo.getTotalCount(),rlist);
        return new ResultWrap<PageInfo<List<UserFlowChartRsponse>>>(Constants.Status.SUCCESS,resutl);
    }

    /**
     * 编辑
     * @param categoryId 分类ID
     * @param price 价格
     * @param comsumeTime 消费时间
     * @param remark 备注
     * @param id
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultWrap update(@RequestParam(value = "categoryId",required = true) Long categoryId,
                             @RequestParam(value = "price",required = true) BigDecimal price,
                             @RequestParam(value = "comsumeTime",required = true) String comsumeTime,
                             @RequestParam(value = "remark",required = true) String remark,
                             @RequestParam(value = "id",required = true) long id){
        UserFlow userFlow = new UserFlow();
        userFlow.setId(id);
        userFlow.setPrice(price);
        userFlow.setComsumeTime(Timestamp.valueOf(comsumeTime));
        userFlow.setCategoryId(categoryId);
        userFlow.setRemark(remark);
        boolean update = userFlowService.update(userFlow);
        return new ResultWrap<>(Constants.Status.SUCCESS,update);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultWrap delete(@RequestParam(value = "id",required = true) long id){
        boolean delete = userFlowService.delete(id);
        return new ResultWrap<>(Constants.Status.SUCCESS,delete);
    }


    /**
     * 按分类展示
     * @return
     */
    @RequestMapping(value = "/categoryChart",method = RequestMethod.GET)
    @ResponseBody
    public ResultWrap categoryChart(){
        List<Map<String, Object>> maps = userFlowService.categoryChart();
        return new ResultWrap<>(Constants.Status.SUCCESS,maps);
    }

    /**
     * 按月展示
     * @return
     */
    @RequestMapping(value = "/monthChart",method = RequestMethod.GET)
    @ResponseBody
    public ResultWrap monthChart(){
        List<Map<String, Object>> maps = userFlowService.monthCahrt();
        return new ResultWrap<>(Constants.Status.SUCCESS,maps);
    }


    /**
     * 按月分类汇总
     * @return
     */
    @RequestMapping(value = "/cateMontChart",method = RequestMethod.GET)
    @ResponseBody
    public ResultWrap cateMontChart(){
        UserFlowCateMontChartResponse list = userFlowService.cateMontChart();

        return new ResultWrap<>(Constants.Status.SUCCESS,list);
    }


    /**
     * 按年汇总
     * @return
     */
    @RequestMapping(value = "/yaerChart",method = RequestMethod.GET)
    @ResponseBody
    public ResultWrap yaerChart(){
        List<Map<String, Object>> maps = userFlowService.selectYears();
        return new ResultWrap<>(Constants.Status.SUCCESS,maps);
    }
}
