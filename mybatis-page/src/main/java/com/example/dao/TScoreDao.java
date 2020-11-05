package com.example.dao;

import com.example.entity.TScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TScore)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-06 14:45:28
 */
public interface TScoreDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TScore queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TScore> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tScore 实例对象
     * @return 对象列表
     */
    List<TScore> queryAll(TScore tScore);

    /**
     * 新增数据
     *
     * @param tScore 实例对象
     * @return 影响行数
     */
    int insert(TScore tScore);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TScore> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TScore> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TScore> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TScore> entities);

    /**
     * 修改数据
     *
     * @param tScore 实例对象
     * @return 影响行数
     */
    int update(TScore tScore);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}