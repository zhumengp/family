package org.zhump.familybill.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.zhump.familybill.controller.response.LoginUserRsponse;

public class SecurityContextUtils {

    /**
     * 获取用户信息
     * @return
     */
    public static LoginUserRsponse getLoginUserinfo(){
        return (LoginUserRsponse)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
