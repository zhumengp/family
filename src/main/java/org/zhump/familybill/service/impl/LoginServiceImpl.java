package org.zhump.familybill.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.dao.UserDao;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.LoginService;
import org.zhump.familybill.util.JwtUtil;
import org.zhump.familybill.util.Md5Util;

import java.util.*;

/**
 * 用户服务层
 * @author zhump
 * @date 2021-02-21 20:42
 */
@Service
@Log4j2
public class LoginServiceImpl implements LoginService {




    @Autowired
    private UserDao userDao;


    @Override
    public LoginUserRsponse login(String accountName, String password) throws Exception{
        LoginUserRsponse loginUserRsponse = new LoginUserRsponse();
        //校验用户回是否是我需要账号
        User user = userDao.findByAccountName(accountName);
        if (user == null){
            throw new BusinessException("用户名或者账户密码错误");
        }
        //旧密码
        String oldPassword=user.getPassword();
        //新密码
        String newPassword = Md5Util.getMd5Str(password+user.getSalt());
        //比对密码
        if(!newPassword.equals(oldPassword)){
            throw new BusinessException("用户名或者账户密码错误");
        }
        BeanUtils.copyProperties(user,loginUserRsponse);
        String token = JwtUtil.getToken(String.valueOf(user.getId()));
        loginUserRsponse.setToken(token);
        return loginUserRsponse;
    }

    @Override
    public User findByAccountName(String accountName) {
        return userDao.findByAccountName(accountName);
    }
}
