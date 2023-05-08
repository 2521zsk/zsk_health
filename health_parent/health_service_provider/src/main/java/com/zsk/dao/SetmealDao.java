package com.zsk.dao;

import com.zsk.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);

    public void setSetmealAndCheckgroup(Map<String, Integer> map);
}
