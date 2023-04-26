package com.zsk.service.impl;

import com.zsk.dao.CheckItemDao;
import com.zsk.pojo.CheckItem;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)context.getBean("sqlSessionFactory");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CheckItem checkItem = new CheckItem();
        checkItem.setName("11");
        checkItem.setCode("11");
        CheckItemDao mapper = sqlSession.getMapper(CheckItemDao.class);
        System.out.println(mapper);
        mapper.add(checkItem);
    }
}
