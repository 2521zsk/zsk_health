package com.zsk.dao;

import com.github.pagehelper.Page;
import com.zsk.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);

    public void setSetmealAndCheckgroup(Map<String, Integer> map);

    public Page<Setmeal> selectByCondition(String queryString);
}
