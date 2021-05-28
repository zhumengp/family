package org.zhump.familybill.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhump.familybill.dao.UserTestDao;
import org.zhump.familybill.module.UserTest;
import org.zhump.familybill.service.UserTestService;
import org.zhump.familybill.util.Md5Util;

/**
 * TODO 描述这个类是干嘛用的
 *
 * @author zhump
 * @date 2021/5/27 23:45
 **/
@Service
public class UserTestServiceImpl implements UserTestService {

   @Autowired
   private UserTestDao userTestDao;


   @Override
   public int update(UserTest userTest) {
      return userTestDao.update(userTest);
   }

   @Override
   public UserTest select(String id) {
      return userTestDao.select(id);
   }

   @Override
   public int deductionPoints(String id, int points) {
      //校验秘钥
      UserTest select1 = userTestDao.select(id);
      String oldseckey = select1.getSeckey();
      System.out.println("旧秘钥:[{}]"+oldseckey);
      //加密
      String md5Str1 = Md5Util.getMd5Str(select1.getPoints() + "-");
      System.out.println("新秘钥----:[{}]"+md5Str1);
      if (!oldseckey.equals(md5Str1)){
         System.out.println("扣除秘钥检测到秘钥不等");
         return 0;
      }
      //扣除积分
      int i = userTestDao.updateId(points, id);
      if(i<=0){
         System.out.println("扣除积分异常，id:[{}],points:[{}]"+id+"---------s"+points);
      }
      //查询最新积分进行加密
      UserTest select = userTestDao.select(id);
      System.out.println("扣除之后的数据:[{}]"+select.toString());
      //计算秘钥
      String md5Str = Md5Util.getMd5Str(select.getPoints()+"-");
      System.out.println("扣除积分之后的秘钥数据:[{}]"+md5Str);
      //更新秘钥
      UserTest userTest = new UserTest();
      userTest.setSeckey(md5Str1);
      userTest.setId(select.getId());
      int update = userTestDao.update(userTest);
      System.out.println(update > 0 ? "更新秘钥成功" : "更新秘钥失败");
      return 0;
   }

   @Override
   public void addPoints(String id, int points) {
      //校验秘钥
      UserTest select1 = userTestDao.select(id);
      System.out.println("旧秘钥:[{}]"+select1.getSeckey());
      //加密
      String md5Str1 = Md5Util.getMd5Str(select1.getPoints() + "-");
      System.out.println("新秘钥----:[{}]"+md5Str1);
      if (!select1.getSeckey().equals(md5Str1)){
         System.out.println("扣除秘钥检测到秘钥不等");
         return;
      }
      //扣除积分
      int i = userTestDao.updatePointsAdd(points, id);
      if(i<=0){
         System.out.println("加积分异常，id:[{}],points:[{}]"+id+points);
      }
      //查询最新积分进行加密
      UserTest select = userTestDao.select(id);
      System.out.println("发放之后的数据:[{}]"+select.toString());
      //计算秘钥
      String md5Str = Md5Util.getMd5Str(select.getPoints()+"-");
      System.out.println("发放之后的秘钥数据:[{}]"+md5Str);
      //更新秘钥
      UserTest userTest = new UserTest();
      userTest.setSeckey(md5Str1);
      userTest.setId(select.getId());
      int update = userTestDao.update(userTest);
      System.out.println(update > 0 ? "更新秘钥成功" : "更新秘钥失败");
   }
}
