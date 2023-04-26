package com.zsk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zsk.dao.CheckItemDao;
import com.zsk.entity.PageResult;
import com.zsk.entity.QueryPageBean;
import com.zsk.pojo.CheckItem;
import com.zsk.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //检查项分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        //mybatis的分页助手插件
        //线程拼接，会将数据从线程提取出来，到时拼接到执行sql的线程
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> pages = checkItemDao.selectByCondition(queryString);
        long total = pages.getTotal();
        List<CheckItem> rows = pages.getResult();
        return new PageResult(total, rows);
    }
}
