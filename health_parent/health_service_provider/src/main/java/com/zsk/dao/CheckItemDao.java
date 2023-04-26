package com.zsk.dao;

import com.github.pagehelper.Page;
import com.zsk.entity.PageResult;
import com.zsk.entity.QueryPageBean;
import com.zsk.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);

    //检查项分页查询
    public Page<CheckItem> selectByCondition(String queryString);
}
