package org.zhump.familybill.filter;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.UserService;
import org.zhump.familybill.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@Title LoginInterceptor
 *@Description: 登录拦截器
 *
 *@author zhump
 *@date 2021/5/23 0:15
 */
@Component
@Log4j2
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 请求前置
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("【拦截器】：进入拦截器,请求地址:[{}]",request.getRequestURI()+",请求参数:[{}]",request.getParameterNames());
        //获取请求头信息
        String authentication = JwtUtil.getAuthentication(request);

        if (StringUtils.isBlank(authentication)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":\"400\",\"msg\":\"请登录\",\"data\":\"\"}");
            return false;
        }
        //查询用户信息
        User byid = userService.getByid(Long.valueOf(authentication));
        if (byid == null){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":\"400\",\"msg\":\"请登录\",\"data\":\"\"}");
            return false;
        }
        return true;
    }

    /**
     * 请求后置
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 页面绚烂前置
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
