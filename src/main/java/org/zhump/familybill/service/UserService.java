package org.zhump.familybill.service;

import org.zhump.familybill.controller.request.UserUpdateRequest;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.User;

import java.util.List;

/**
 *@Title UserService
 *@Description: 用户服务层
 *
 *@author zhump
 *@date 2021/4/10 20:44
 */
public interface UserService {

    /**
     * 用户分页

     * @param pageIndex 当前页
     * @param pageSize 当前页条数
     * @author zhump
     * @return org.zhump.familybill.module.PageInfo<java.util.List<org.zhump.familybill.module.User>>
     * @date 2021/4/10 20:44
     * @throws Exception
     */
    PageInfo<List<User>> selectAll(Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 查询所有用户数据
     *
     * @author zhump
     * @return java.util.List<org.zhump.familybill.module.User>
     * @date 2021/4/10 20:48
     * @throws  Exception
     */
    List<User> selectAll() throws Exception;

    /**
     * 新增
     *
     * @param user
     * @author zhump
     * @return boolean
     * @date 2021/4/10 20:45
     * @throws Exception
     */
    boolean insert(User user) throws Exception;

    /**
     * 通过ID查询用户信息
     *
     * @param id
     * @author zhump
     * @return org.zhump.familybill.module.User
     * @date 2021/4/10 20:49
     * @throws Exception
     */
    User getByid(long id) throws Exception;

    /**
     * 更新操作
     *
     * @param user
     * @author zhump
     * @return boolean
     * @date 2021/4/10 20:46
     * @throws Exception
     */
    boolean update(User user) throws Exception;

   /**
    * 删除操作
    *
    * @param id
    * @author zhump
    * @return boolean
    * @date 2021/4/10 20:46
    * @throws Exception
    */
    boolean delete(long id) throws Exception;

    /**
     * 根据用户账号名称查询
     * @param name
     * @return
     */
    User findAccountName(String name);

}
