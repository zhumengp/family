package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.zhump.familybill.module.UserCategory;


/**
 *@Title UserCategoryDao
 *@Description: 分类管理
 *
 *@author zhump
 *@date 2021/4/10 20:30
 */
@Mapper
public interface UserCategoryDao {

    /**
     * 新增分类
     * @param userCategory:
     * @author zhump
     * @return int
     * @date 2021/4/10 20:29
     * @throws
     */
    int insert(UserCategory userCategory);
}
