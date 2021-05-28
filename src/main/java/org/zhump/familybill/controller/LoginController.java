package org.zhump.familybill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhump.familybill.contants.Constants;
import org.zhump.familybill.contants.ResultWrap;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.service.LoginService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

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

    /**
     * 新增
     * @param accountName 用户名
     * @param password 用户名
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResultWrap insert(String accountName, String password){
        try {
            String token = loginService.login(accountName, password);
            Map<String,Object> map = new HashMap<String,Object>(16);
            map.put("token",token);
            return  new ResultWrap(Constants.Status.SUCCESS,map);

        }catch (Exception e){
            if (e instanceof BusinessException){
                return new ResultWrap(Constants.Status.FAIL,e.getMessage());
            }
            return new ResultWrap<>(Constants.Status.Error,"");
        }
    }

}



