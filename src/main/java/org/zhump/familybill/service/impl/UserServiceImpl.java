package org.zhump.familybill.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhump.familybill.controller.exception.BusinessException;
import org.zhump.familybill.dao.UserDao;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.module.User;
import org.zhump.familybill.service.UserService;
import org.zhump.familybill.util.Md5Util;

import java.util.*;

/**
 * 用户服务层
 * @author zhump
 * @date 2021-02-21 20:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     *分页数据
     * @param pageIndex 当前页
     * @param pageSize 当前页条数
     * @return
     */
    @Override
    public PageInfo<List<User>> selectAll(Integer pageIndex, Integer pageSize) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>(16);
        Page<User> page = PageHelper.startPage(pageIndex, pageSize);
        this.userDao.selectAll(map);
        long total = page.getTotal();
        if(total <=0 ) {
            PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(pageIndex,pageSize,total,new ArrayList<>());
            return pageInfo;
        }
        List<User> result = page.getResult();
        PageInfo<List<User>> pageInfo = new PageInfo<List<User>>(pageIndex,pageSize,total,result);
        return pageInfo;
    }

    /**
     * 查询所有
     * @param :
     * @author zhump
     * @return java.util.List<org.zhump.familybill.module.User>
     * @date 2021/4/10 20:32
     * @throws
     */
    @Override
    public List<User> selectAll() {
        Map<String,Object> map = new HashMap<String,Object>(16);
        return this.userDao.selectAll(map);
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(User user) throws Exception{
        //设置新密码
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().replace("-","");
        String newPassword = Md5Util.getMd5Str(oldPassword+salt);
        //设置盐
        user.setSalt(salt);
        user.setPassword(newPassword);
        int res = userDao.insert(user);
        return res >= 0 ? true : false;
    }

    /**
     * 查询一条数据
     * @param id
     * @return
     */
    @Override
    public User getByid(long id) throws Exception{
        return userDao.getByid(id);
    }
    /**
     * 编辑
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) throws Exception{
        //查询账户核实密码和账户
        User byid = userDao.getByid(user.getId());
        if (byid == null){
            throw new BusinessException("没有该账户");
        }
        //设置新密码
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().replace("-","");
        String newPassword = Md5Util.getMd5Str(oldPassword+salt);
        //设置盐
        user.setSalt(salt);
        user.setPassword(newPassword);
        int res = userDao.update(user);
        return res >= 0 ? true : false;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(long id) throws Exception{
        int res = userDao.delete(id);
        return res >= 0 ? true : false;
    }

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    @Override
    public User findAccountName(String name) {
        return userDao.findByAccountName(name);
    }
}
