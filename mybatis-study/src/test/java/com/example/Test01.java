package com.example;

import com.example.dao.UserMapper;
import com.example.entity.User;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.session.SqlSessionFactoryBuilder;

import java.util.List;

public class Test01 {

    public static void main(String[] args) {

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build("conf.properties");
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.getAll();
        for (User user : all) {
            System.out.println(user);
        }


    }
}
