package com.example.dao;


import com.example.entity.User;

import java.util.List;

/**
 * UserMapper.java
 */
public interface UserMapper {

    /**
     * 获取单个user
     *
     * @param id
     * @return
     * @see
     */
    User getUser(int id);

    /**
     * 获取所有用户
     *
     * @return
     * @see
     */
    List<User> getAll();

    /**
     * 更新用户（功能未完成）
     *
     * @param id
     */
    void updateUser(String name,int id);
}
