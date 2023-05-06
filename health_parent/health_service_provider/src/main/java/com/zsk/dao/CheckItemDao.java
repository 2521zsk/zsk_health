package com.zsk.dao;

import com.github.pagehelper.Page;
import com.zsk.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    //检查项添加操作
    public void add(CheckItem checkItem);

    //检查项分页查询
    public Page<CheckItem> selectByCondition(String queryString);

    //通过id查询与检查组表的关联条数
    public long findCountById(Integer id);

    //通过id删除检查项
    public void deleteById(Integer id);

    //编辑检查项
    public void edit(CheckItem checkItem);

    //通过id查找检查项
    public CheckItem findById(Integer id);

    //查找全部检查项
    public List<CheckItem> findAll();
}
