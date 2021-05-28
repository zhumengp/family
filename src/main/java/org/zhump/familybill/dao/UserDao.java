package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.zhump.familybill.module.User;

import java.util.List;
import java.util.Map;


/**
 * 用户管理
 * @author zhump
 */
@Mapper
public interface UserDao {

    /**
     * 查询所有
     * @param map:
     * @author zhump
     * @return java.util.List<org.zhump.familybill.module.User>
     * @date 2021/4/10 20:30
     * @throws
     */
    List<User> selectAll(Map<String,Object> map);

   /**
    * 新增用户
    * @param user:
    * @author zhump
    * @return int
    * @date 2021/4/10 20:30
    * @throws
    */
    int insert(User user);

   /**
    * 单条数据查询
    * @param id:
    * @author zhump
    * @return org.zhump.familybill.module.User
    * @date 2021/4/10 20:30
    * @throws
    */
    User getByid(long id);

    /**
     * 编辑
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(long id);
}
