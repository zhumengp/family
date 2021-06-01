package org.zhump.familybill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zhump.familybill.contants.Constants;
import org.zhump.familybill.contants.ResultWrap;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.service.LoginService;
import org.zhump.familybill.util.RedisUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhmp
 * @description 登录类
 * @date $ $
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 新增
     * @param accountName 用户名
     * @param password 用户名
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResultWrap insert(String accountName, String password){
        try {
            LoginUserRsponse login = loginService.login(accountName, password);
            //将用户token存入redis
            return  new ResultWrap(Constants.Status.SUCCESS,login);

        }catch (Exception e){
            if (e instanceof BusinessException){
                return new ResultWrap(Constants.Status.FAIL,e.getMessage());
            }
            return new ResultWrap<>(Constants.Status.Error,"");
        }
    }

}



