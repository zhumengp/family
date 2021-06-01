package org.zhump.familybill.service;

import org.zhump.familybill.controller.response.LoginUserRsponse;
import org.zhump.familybill.module.User;

/**
 *@Title LoginService
 *@Description: 登录服务层
 *
 *@author zhump
 *@date 2021/4/10 20:56
 */
public interface LoginService {

    /**
     *  登录
     * @param accountName:
     * @param password:
     * @author zhump
     * @return java.lang.String
     * @date 2021/4/10 20:29
     * @throws Exception
     */
    LoginUserRsponse login(String accountName, String password)  throws Exception;


    /**
     *  根据用户账号查询
     * @param accountName:
     * @author zhump
     * @return java.lang.String
     * @date 2021/4/10 20:29
     * @throws Exception
     */
    User findByAccountName(String accountName);
}
