package org.zhump.familybill.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.LoginService;
import org.zhump.familybill.service.UserService;
import org.zhump.familybill.util.JwtUtil;
import org.zhump.familybill.util.Md5Util;

/**
 * 数据库校验身份
 */
public class DbAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户名
        String accountName = authentication.getName();
        //判断用户名是否为空
        if (StringUtils.isBlank(accountName)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        //密码
        String password = authentication.getCredentials().toString();
        if (StringUtils.isBlank(password)){
            throw new CredentialsExpiredException("密码错误");
        }
        //查询数据库
        LoginUserRsponse loginUserRsponse = new LoginUserRsponse();
        //校验用户回是否是我需要账号
        User user = loginService.findByAccountName(accountName);
        if (user == null){
            throw new UsernameNotFoundException("用户名或者账户密码错误");
        }
        //旧密码
        String oldPassword=user.getPassword();
        //新密码
        String newPassword = Md5Util.getMd5Str(password+user.getSalt());
        //比对密码
        if(!newPassword.equals(oldPassword)){
            throw new UsernameNotFoundException("用户名或者账户密码错误");
        }
        BeanUtils.copyProperties(user,loginUserRsponse);
        String token = JwtUtil.getToken(String.valueOf(user.getId()));
        loginUserRsponse.setToken(token);
        return new UsernamePasswordAuthenticationToken(loginUserRsponse,loginUserRsponse.getToken());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
