package org.zhump.familybill.module;

import lombok.Data;

/**
 * TODO 描述这个类是干嘛用的
 *
 * @author zhump
 * @date 2021/5/27 23:41
 **/
@Data
public class UserTest {


   private String id;
   /**
    * 积分
    */
   private Integer points;

   /**
    * 秘钥
    */
   private String seckey;


}
