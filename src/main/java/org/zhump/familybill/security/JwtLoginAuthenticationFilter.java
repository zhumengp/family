package org.zhump.familybill.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.zhump.familybill.contants.PerssionConfig;
import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.UserService;
import org.zhump.familybill.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
@Log4j2
public class JwtLoginAuthenticationFilter extends BasicAuthenticationFilter {


    private final UserService userService;

    public JwtLoginAuthenticationFilter(AuthenticationManager authenticationManager,UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    /**
     * 过滤器
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("进入 JwtLoginAuthenticationFilter的doFilterInternal()请求:[{}],请求参数:[{}]",request.getRequestURI(),request.getParameterNames());
        //获取请求头信息
        String authentication = JwtUtil.getAuthentication(request);
        if (StringUtils.isBlank(authentication)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":\"400\",\"msg\":\"请登录\",\"data\":\"\"}");
            return;
        }

        //查询用户信息
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(Long.valueOf(authentication));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }

    /**
     * 解析token中的信息
     * 这里走数据库查询一遍数据权限，是非登录接口进来，所以这里要重新设置一遍权限
     */
    private UsernamePasswordAuthenticationToken getAuthentication(Long id) {
        try {
            User user = userService.getByid(id);
            if (user != null) {
                LoginUserRsponse loginUserRsponse = new LoginUserRsponse();
                BeanUtils.copyProperties(user,loginUserRsponse);
                String token = JwtUtil.getToken(String.valueOf(user.getId()));
                loginUserRsponse.setToken(token);
                loginUserRsponse.setAuthorities(PerssionConfig.getPerssionList().stream().map(u->u).toArray(String[]::new));
                return new UsernamePasswordAuthenticationToken(loginUserRsponse,loginUserRsponse.getAccountName(), AuthorityUtils.createAuthorityList(loginUserRsponse.getAuthorities()));
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
