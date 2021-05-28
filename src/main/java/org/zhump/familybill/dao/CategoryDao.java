package org.zhump.familybill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.zhump.familybill.module.Category;

import java.util.List;

/**
 *@Title CategoryDao
 *@Description: 分类
 *
 *@author zhump
 *@date 2021/4/10 20:52
 */
@Mapper
public interface CategoryDao {

    /**
     * 查询所有
     * @return
     */
    List<Category> selectAll();

    /**
     * 查询单挑
     * @param id
     * @return
     */
    Category getByid(long id);

    /**
     * 新增
     * @param category
     * @return
     */
    int insert(Category category);

    /**
     * 编辑
     * @param category
     * @return
     */
    int update(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 查询分类ID
     * @param :
     * @author zhump
     * @return java.util.List<org.zhump.familybill.module.Category>
     * @date 2021/4/10 20:28
     * @throws
     */
    List<Category> categoryId();
}
