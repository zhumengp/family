package org.zhump.familybill.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 登录用户返回信息
 */
@Data
public class LoginUserRsponse {

    /**
     * token
     */
    private String token;

    private long id;

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    /**
     * 年龄
     */
    private Integer age;

    private String[] authorities;


}
