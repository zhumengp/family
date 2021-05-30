package org.zhump.familybill.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.service.LoginService;
import org.zhump.familybill.service.UserService;

/**
 * 数据库校验身份
 */
public class DbAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户名
        String name = authentication.getName();
        //判断用户名是否为空
        if (StringUtils.isBlank(name)){
            throw new UsernameNotFoundException("用户名不能为空");
        }
        //密码
        String password = authentication.getCredentials().toString();
        if (StringUtils.isBlank(password)){
            throw new CredentialsExpiredException("密码错误");
        }
        //查询数据库
        try {
            String token = loginService.login(name, password);
            if(StringUtils.isBlank(token)){
                throw new UsernameNotFoundException("用户名或者密码错误");
            }
            //封装用户信息
            LoginUserRsponse loginUserRsponse = new LoginUserRsponse();
            loginUserRsponse.setToken(token);
            return new UsernamePasswordAuthenticationToken(loginUserRsponse,loginUserRsponse.getToken());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
