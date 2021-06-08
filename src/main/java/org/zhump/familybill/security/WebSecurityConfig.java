package org.zhump.familybill.security;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zhump.familybill.service.UserService;

/**
 *
 */
@Configuration
@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DbAuthenticationProvider authenticationProvider;

    @Autowired
    private UserService userService;

    //身份验证管理生成器
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);//配置用户登录过滤器 数据库验证
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/list").permitAll().anyRequest().authenticated();
        http.cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .disable();
        http
                .addFilterBefore(new JwtLoginFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtLoginAuthenticationFilter(authenticationManager(),userService))
                .headers().frameOptions().disable();
    }
}
