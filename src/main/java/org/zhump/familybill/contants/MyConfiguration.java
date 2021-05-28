package org.zhump.familybill.contants;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhump.familybill.filter.LoginInterceptor;

/**
 *@Title MyConfiguration
 *@Description: 跨域设置
 *
 *@author zhump
 *@date 2021/4/10 20:57
 */
@Configuration
public class MyConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 支持跨域请求
     * @return
     */
	@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //1,允许任何来源
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        //2,允许任何请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //3,允许任何方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        //4,允许凭证
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

   /**
    * 加入拦截
    *
    * @param registry
    * @author zhump
    * @return void
    * @date 2021/4/10 20:58
    * @throws
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

	    registry.addInterceptor(loginInterceptor).excludePathPatterns("/login/**");

    }
}