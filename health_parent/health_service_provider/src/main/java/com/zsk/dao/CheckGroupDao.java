package com.zsk.dao;

import com.github.pagehelper.Page;
import com.zsk.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);
}
