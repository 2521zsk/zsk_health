package com.zsk.service;

import com.zsk.entity.PageResult;
import com.zsk.entity.QueryPageBean;
import com.zsk.pojo.CheckGroup;
import com.zsk.pojo.CheckItem;

import java.util.List;

/**
 * 检查组服务接口
 */

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
