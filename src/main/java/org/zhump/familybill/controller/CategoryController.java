package org.zhump.familybill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhump.familybill.contants.Constants;
import org.zhump.familybill.contants.Result;
import org.zhump.familybill.contants.ResultWrap;
import org.zhump.familybill.module.Category;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.service.CategoryService;

import java.util.List;


/**
 *@Title CategoryController
 *@Description: 分类控制层
 *
 *@author zhump
 *@date 2021/4/10 20:50
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 新增分离
     *
     * @param name 名称
     * @author zhump
     * @return org.zhump.familybill.contants.Result
     * @date 2021/4/10 20:50
     * @throws
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestParam(value = "name",required = true) String name){
        categoryService.insert(name);
        return  new Result(200,"操作成功","");
    }

    /**
     * 查询所有分类
     *
     * @author zhump
     * @return org.zhump.familybill.contants.ResultWrap<java.util.List<org.zhump.familybill.module.Category>>
     * @date 2021/4/10 20:51
     * @throws
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultWrap<List<Category>> list(){
        List<Category> result = categoryService.selectAll();
        return  new ResultWrap<List<Category>>(Constants.Status.SUCCESS,result);
    }

    /**
     * 分页
     *
     * @param pageIndex
     * @param pageSize
     * @author zhump
     * @return org.zhump.familybill.contants.ResultWrap<org.zhump.familybill.module.PageInfo<java.util.List<org.zhump.familybill.module.Category>>>
     * @date 2021/4/10 20:51
     * @throws
     */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public ResultWrap<PageInfo<List<Category>>> page(@RequestParam(value="pageIndex",required= true,defaultValue = "1")Integer pageIndex,
                                           @RequestParam(value="pageSize",required= true,defaultValue = "10")Integer pageSize){
        PageInfo<List<Category>> result = categoryService.selectAll(pageIndex, pageSize);
        return  new ResultWrap<PageInfo<List<Category>>>(Constants.Status.SUCCESS,result);
    }

    /**
     * 编辑
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultWrap update(@RequestParam(value="id",required= true) long id,
                                                     @RequestParam(value="name",required= true)String name){
        boolean update = categoryService.update(id, name);
        return  new ResultWrap(Constants.Status.SUCCESS,update);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultWrap delete(@RequestParam(value = "id",required = true) long id){
        boolean delete = categoryService.delete(id);
        if (delete){
            return new ResultWrap<>(Constants.Status.SUCCESS,delete);
        }
        return new ResultWrap<>(Constants.Status.FAIL,delete);

    }
}
