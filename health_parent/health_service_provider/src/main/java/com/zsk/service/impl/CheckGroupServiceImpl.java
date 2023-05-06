package com.zsk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zsk.dao.CheckGroupDao;
import com.zsk.pojo.CheckGroup;
import com.zsk.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 检查组服务
 */

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时检查组要关联检查项
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //1.添加checkGroup到checkgroup表中
        checkGroupDao.add(checkGroup);
        //2.关联检查项
        //已通过selectKey对id进行了赋值
        Integer checkGroupId = checkGroup.getId();
        if(checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
