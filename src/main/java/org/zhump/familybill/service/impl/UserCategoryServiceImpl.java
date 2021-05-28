package org.zhump.familybill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhump.familybill.dao.UserCategoryDao;
import org.zhump.familybill.module.UserCategory;
import org.zhump.familybill.service.UserCategoryService;
/**
 *@Title BusinessException
 *@Description: 业务异常类
 *
 *@author zhump
 *@date 2021/4/10 20:49
 */
@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryDao userCategoryDao;

    @Override
    public boolean insert(long userId,long categoryId) {
        UserCategory userCategory = new UserCategory();
        userCategory.setUserId(userId);
        userCategory.setCategoryId(categoryId);
        int res = userCategoryDao.insert(userCategory);
        return res >= 0 ? true :false;
    }
}
