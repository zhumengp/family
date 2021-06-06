package org.zhump.familybill.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zhump.familybill.controller.response.LoginUserRsponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/***
 * 登陆成功回调
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Log log = LogFactory.getLog(LoginSuccessHandler.class);

	/**
	 * 身份校验成功回调
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoginUserRsponse principal = (LoginUserRsponse)authentication.getPrincipal();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(principal.getToken());
		out.flush();
		out.close();
	}
}
