package org.zhump.familybill.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhump.familybill.dao.CategoryDao;
import org.zhump.familybill.module.Category;
import org.zhump.familybill.module.PageInfo;
import org.zhump.familybill.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类服务实现类
 * @author zhump
 */
@Service
public class CategroyServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public boolean insert(String name) {
        Category category = new Category();
        category.setRemark(name);
        int res = categoryDao.insert(category);
        return res >= 0 ? true :false;
    }

     /**
      * TODO 描述一下这个方法是干嘛用的
      * @param pageIndex:
         * @param pageSize:
      * @author zhump
      * @return org.zhump.familybill.module.PageInfo<java.util.List<org.zhump.familybill.module.Category>>
      * @date 2021/4/10 20:19
      * @throws
      */
    @Override
    public PageInfo<List<Category>> selectAll(Integer pageIndex,Integer pageSize) {
       categoryDao.selectAll();
        Page<Category> page = PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> map = new HashMap<>(16);
        this.categoryDao.selectAll();
        long total = page.getTotal();
        if(total <=0 ) {
            PageInfo<List<Category>> pageInfo = new PageInfo<List<Category>>(pageIndex,pageSize,total,new ArrayList<>());
            return pageInfo;
        }
        List<Category> result = page.getResult();
        PageInfo<List<Category>> pageInfo = new PageInfo<List<Category>>(pageIndex,pageSize,total,result);
        return pageInfo;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> selectAll() {
        return this.categoryDao.selectAll();
    }

    /**
     * 查询单条
     * @param id 主键
     * @return
     */
    @Override
    public Category getById(long id) {
        return categoryDao.getByid(id);
    }

    /**
     * 编辑
     * @param id 主键
     * @param name 名称
     * @return
     */
    @Override
    public boolean update(long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setRemark(name);
        int update = categoryDao.update(category);
        return update >= 0 ? true : false;
    }

    @Override
    public boolean delete(long id) {
        int res = categoryDao.delete(id);
        return res >= 0 ? true : false;

    }

    @Override
    public List<Category> categoryId() {
        return categoryDao.categoryId();
    }
}
