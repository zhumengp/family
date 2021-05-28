package org.zhump.familybill.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.zhump.familybill.controller.exception.BusinessException;
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
    public String login(String accountName, String password) throws Exception {
        Map<String,Object> map = new HashMap<>(16);
        map.put("accountName",accountName);
        //校验用户回是否是我需要账号
        List<User> users = userDao.selectAll(map);
        if (users == null | users.size() <=0){
            throw new BusinessException("用户名或者账户密码错误");

        }
        //校验用户密码
        //旧密码
        String oldPassword=users.get(0).getPassword();
        String salt = users.get(0).getSalt();
        //新密码
        String newPassword = Md5Util.getMd5Str(password+salt);
        //新密码
        if(!newPassword.equals(oldPassword)){
            throw new BusinessException("用户名或者账户密码错误");
        }
        String token = JwtUtil.getToken(users.get(0).getId()+"");
        return token;
    }
}
