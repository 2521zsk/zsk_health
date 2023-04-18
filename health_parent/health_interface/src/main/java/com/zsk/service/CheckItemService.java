package com.zsk.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zsk.pojo.CheckItem;

/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);
}
