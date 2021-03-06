package org.zhump.familybill.controller.request;

import lombok.Data;

/**
 *@Title UserInsertRequest
 *@Description: 用户类信息
 *
 *@author zhump
 *@date 2021/4/11 21:53
 */
@Data
public class UserInsertRequest {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String imageUrl;
    /**
     * 手机号
     */
    private String phone;
    /**
     * qq
     */
    private String qq;
    /**
     * 微信
     */
    private String wx;
    /**
     * 登录账户
     */
    private String accountName;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 是否禁用
     */
    private Integer locked;
}
