package org.zhump.familybill.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.zhump.familybill.contants.PerssionConfig;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.LoginService;
import org.zhump.familybill.service.UserService;
import org.zhump.familybill.util.JwtUtil;
import org.zhump.familybill.util.Md5Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库校验身份
 */
@Component
@Log4j2
public class DbAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    /**
    * Title:
    * Description:这里登录设置返回权限，主要是返回给前端进行权限控制
     *
    * @author zhump
    * @version 1.0.0
    * @date 2021/6/9 0:38
    */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("进入 DbAuthenticationProvider：authenticate()...");
        //用户名
        String accountName = authentication.getName();
        //判断用户名是否为空
        if (StringUtils.isBlank(accountName)){
            throw new AuthenticationException("用户名不能为空"){
                private static final long serialVersionUID = 4241241525163064123L;
            };
        }
        //密码
        String password = authentication.getCredentials().toString();
        if (StringUtils.isBlank(password)){
            throw new AuthenticationException("密码错误"){
                private static final long serialVersionUID = 4241241525163064123L;
            };
        }
        //查询数据库
        LoginUserRsponse loginUserRsponse = new LoginUserRsponse();
        //校验用户回是否是我需要账号
        User user = loginService.findByAccountName(accountName);
        if (user == null){
            throw new AuthenticationException("用户名或者账户密码错误"){
                private static final long serialVersionUID = 4241241525163064123L;
            };
        }
        //旧密码
        String oldPassword=user.getPassword();
        //新密码
        String newPassword = Md5Util.getMd5Str(password+user.getSalt());
        //比对密码
        if(!newPassword.equals(oldPassword)){
            throw new AuthenticationException("用户名或者账户密码错误"){
                private static final long serialVersionUID = 4241241525163064123L;
            };
        }
        BeanUtils.copyProperties(user,loginUserRsponse);
        String token = JwtUtil.getToken(String.valueOf(user.getId()));
        loginUserRsponse.setToken(token);
        loginUserRsponse.setAuthorities(PerssionConfig.getPerssionList().stream().map(u->u).toArray(String[]::new));
        return new UsernamePasswordAuthenticationToken(loginUserRsponse,loginUserRsponse.getAccountName(), AuthorityUtils.createAuthorityList(loginUserRsponse.getAuthorities()));
    }

    @Override
    public boolean supports(Class<?> authenticatetion) {
        return true;
    }
}
