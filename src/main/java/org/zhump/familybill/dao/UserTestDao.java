package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zhump.familybill.module.User;
import org.zhump.familybill.module.UserTest;

/**
 * TODO 描述这个类是干嘛用的
 *
 * @author zhump
 * @date 2021/5/27 23:43
 **/
@Mapper
public interface UserTestDao {

    int update(UserTest userTest);

    int updateId(@Param("points") int points,@Param("id") String id);


    int updatePointsAdd(@Param("points") int points,@Param("id") String id);


    UserTest select(@Param("id") String id);
}
