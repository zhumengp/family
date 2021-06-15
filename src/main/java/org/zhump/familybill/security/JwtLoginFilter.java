package org.zhump.familybill.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.zhump.familybill.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter  {

    private AuthenticationManager authenticationManager;


    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        //设置拦截规则
        super(new AntPathRequestMatcher("/login/", "POST"));
        setAuthenticationSuccessHandler(new LoginSuccessHandler());
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("进入 JwtLoginFilter：attemptAuthentication()...");
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        if(StringUtils.isBlank(accountName) || StringUtils.isBlank(password)){
            throw new AuthenticationException("登录参数异常"){
                private static final long serialVersionUID = 4241241525163064123L;
            };
        }
        log.info("进入 JwtLoginFilter：初始化UsernamePasswordAuthenticationToken()");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName,password));
    }
}
