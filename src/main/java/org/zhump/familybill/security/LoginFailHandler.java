package org.zhump.familybill.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * 登陆失败回调
 */
public class LoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 身份校验失败回调
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        if (exception instanceof AuthenticationException) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"code\":\"400\",\"msg\":\"操作失败\",\"data\":"+exception.getMessage()+"}");
            out.flush();
            out.close();
        } else {
            throw exception;
        }
    }
}

