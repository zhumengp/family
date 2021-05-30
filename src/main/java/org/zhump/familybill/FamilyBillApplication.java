package org.zhump.familybill;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;


/**
 *@Title FamilyBillApplication
 *@Description: 主方法
 *
 *@author zhump
 *@date 2021/4/10 20:25
 */
@SpringBootApplication
@Log4j2
public class FamilyBillApplication extends SpringBootServletInitializer {

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(FamilyBillApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FamilyBillApplication.class);
    }
}
