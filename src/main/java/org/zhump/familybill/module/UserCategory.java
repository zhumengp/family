package org.zhump.familybill.module;

import lombok.Data;

/**
 *@Title UserCategory
 *@Description: 用户分类
 *
 *@author zhump
 *@date 2021/4/10 20:59
 */
@Data
public class UserCategory {

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 分类ID
     */
    private long categoryId;
}
