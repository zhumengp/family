package org.zhump.familybill;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



/**
 *@Title FamilyBillApplication
 *@Description: 主方法
 *
 *@author zhump
 *@date 2021/4/10 20:25
 */
@SpringBootApplication
public class FamilyBillApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
//        System.out.println("-------------------开启线程----------------");
//        Thread thread = new Thread(new DeductionPointsRunable());
//        thread.setName("扣积分线程");
//        thread.start();
//
//        Thread t2 = new Thread(new SendPointsRunable());
//        t2.setName("加积分线程");
//        t2.start();
//        System.out.println("-------------------积分操作结束-----------------");
        SpringApplication.run(FamilyBillApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FamilyBillApplication.class);

    }
}
