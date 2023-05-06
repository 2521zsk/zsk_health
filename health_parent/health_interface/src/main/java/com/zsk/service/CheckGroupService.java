package com.zsk.service;

import com.zsk.pojo.CheckGroup;

/**
 * 检查组服务接口
 */

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
