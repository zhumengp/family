package org.zhump.familybill.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DbAuthenticationProvider authenticationProvider;


    /**
     * 数据库校验身份
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
