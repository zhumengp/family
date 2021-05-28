package org.zhump.familybill.service;

import org.zhump.familybill.module.UserTest;

/**
 * TODO 描述这个类是干嘛用的
 *
 * @author zhump
 * @date 2021/5/27 23:44
 **/
public interface UserTestService {

   public int update(UserTest userTest);

   public UserTest select(String id);

   /**
    * 扣除积分
    */
   public int deductionPoints(String id,int points);

   /**
    * 加积分
    * @param id
    * @param points
    * @return
    */
   public void addPoints(String id,int points);
}
