package com.example.service;

import com.example.entity.TUser;
import com.example.plugin.PageResponse;

import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author makejava
 * @since 2020-10-06 14:17:23
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    PageResponse<List<TUser>> queryByPage(int page, int size);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);



}