package org.zhump.familybill.service;


/**
 *@Title UserCategoryService
 *@Description: 用户分类
 *
 *@author zhump
 *@date 2021/4/10 20:53
 */
public interface UserCategoryService {

    /**
     * 新增
     * @param userId
     * @param categoryId
     * @author zhump
     * @return boolean
     * @date 2021/4/11 21:49
     * @throws
     */
    boolean insert(long userId,long categoryId);
}
