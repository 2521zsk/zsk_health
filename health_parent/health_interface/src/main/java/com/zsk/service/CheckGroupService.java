package com.zsk.service;

import com.zsk.entity.PageResult;
import com.zsk.entity.QueryPageBean;
import com.zsk.pojo.CheckGroup;

/**
 * 检查组服务接口
 */

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
}
