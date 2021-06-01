package org.zhump.familybill.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhump.familybill.contants.Constants;
import org.zhump.familybill.contants.ResultWrap;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.controller.request.UserInsertRequest;
import org.zhump.familybill.controller.request.UserUpdateRequest;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.UserService;

import java.util.List;
/**
 *@Title BusinessException
 *@Description: 业务异常类
 *
 *@author zhump
 *@date 2021/4/10 20:49
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/list")
    public ResultWrap<List<User>> selectAll(){
        try {
            List<User> result = userService.selectAll();
            return new ResultWrap<List<User>>(Constants.Status.SUCCESS,result);
        }catch (Exception e){
            return new ResultWrap(Constants.Status.Error,"");
        }
    }

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/page")
    public ResultWrap page(@RequestParam(value="pageIndex",required= true,defaultValue = "1")Integer pageIndex,
                                                  @RequestParam(value="pageSize",required= true,defaultValue = "10")Integer pageSize){
        try {
            PageInfo<List<User>> result = userService.selectAll(pageIndex, pageSize);
            return new ResultWrap(Constants.Status.SUCCESS,result);
        }catch (Exception e){
            return new ResultWrap(Constants.Status.Error,"");
        }
    }

    /**
     * 新增
     * @param userInsertRequest 姓名
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResultWrap insert(UserInsertRequest userInsertRequest){
        //根据用户账号去数据库里面查询.如果存在,则不允许重复
        User accountName = userService.findAccountName(userInsertRequest.getAccountName());
        if(accountName != null){
            return new ResultWrap<>(Constants.Status.ACCOUNT_NAME);
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(userInsertRequest,user);
            userService.insert(user);
            return  new ResultWrap(Constants.Status.SUCCESS);
        }catch (Exception e){
            if (e instanceof BusinessException){
                return new ResultWrap(Constants.Status.FAIL,e.getMessage());
            }
            return new ResultWrap<>(Constants.Status.Error);
        }
    }


    /**
     * 编辑
     * @param userUpdateRequest 编辑参数
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultWrap update(UserUpdateRequest userUpdateRequest){
        try {
            User user = new User();
            BeanUtils.copyProperties(userUpdateRequest,user);
            boolean update = userService.update(user);
            if (update){
                return new ResultWrap<>(Constants.Status.SUCCESS,update);
            }
            return new ResultWrap<>(Constants.Status.FAIL,update);
        }catch (Exception e){
            if (e instanceof BusinessException){
                return new ResultWrap(Constants.Status.FAIL,e.getMessage());
            }
            return new ResultWrap<>(Constants.Status.Error,"");
        }

    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultWrap delete(@RequestParam(value = "id",required = true) long id){
        try {
            boolean delete = userService.delete(id);
            if (delete){
                return new ResultWrap<>(Constants.Status.SUCCESS,delete);
            }
            return new ResultWrap<>(Constants.Status.FAIL,delete);
        }catch (Exception e){
            if (e instanceof BusinessException){
                return new ResultWrap(Constants.Status.FAIL,e.getMessage());
            }
            return new ResultWrap<>(Constants.Status.Error,"");
        }

    }
}
