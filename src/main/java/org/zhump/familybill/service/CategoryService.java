package org.zhump.familybill.service;

import org.zhump.familybill.module.Category;
import org.zhump.familybill.module.PageInfo;

import java.util.List;

/**
 *@Title CategoryService
 *@Description: 分类服务层接口
 *
 *@author zhump
 *@date 2021/4/10 20:52
 */
public interface CategoryService {

    /**
     * 新增
     * @param name
     * @return
     */
    boolean insert(String name);

    /**
     * 分页查询
     * @param pageIndex:
    	 * @param pageSize:
     * @author zhump
     * @return org.zhump.familybill.module.PageInfo<java.util.List<org.zhump.familybill.module.Category>>
     * @date 2021/4/10 20:29
     * @throws
     */
    PageInfo<List<Category>> selectAll(Integer pageIndex, Integer pageSize);

    /**
     * 查询所有
     * @return
     */
    List<Category> selectAll();

    /**
     * 查询单条
     * @param id
     * @return
     */
    Category getById(long id);

    /**
     * 编辑
     * @param id
     * @param name
     * @return
     */
    boolean update(long id,String name);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 查询分类ID
     * @param :
     * @author zhump
     * @return java.util.List<org.zhump.familybill.module.Category>
     * @date 2021/4/10 20:33
     * @throws
     */
    List<Category> categoryId();
}
