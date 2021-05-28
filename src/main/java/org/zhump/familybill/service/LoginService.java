package org.zhump.familybill.service;

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
    String login(String accountName, String password) throws Exception;
}
