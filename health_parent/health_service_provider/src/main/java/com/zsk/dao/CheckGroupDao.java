package com.zsk.dao;

import com.github.pagehelper.Page;
import com.zsk.pojo.CheckGroup;
import com.zsk.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByGroupId(Integer id);

    public void update(CheckGroup checkGroup);

    public void deleteCheckItemIdsByCheckGroupId(Integer id);

    public List<CheckGroup> findAll();
}
