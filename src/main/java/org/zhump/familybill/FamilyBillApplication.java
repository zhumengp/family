package org.zhump.familybill;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 *@Title FamilyBillApplication
 *@Description: 主方法
 *
 *@author zhump
 *@date 2021/4/10 20:25
 */
@EnableScheduling
@SpringBootApplication
@Log4j2
public class FamilyBillApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FamilyBillApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FamilyBillApplication.class);
    }
}
