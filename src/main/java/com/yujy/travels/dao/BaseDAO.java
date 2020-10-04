package com.yujy.travels.dao;

import com.yujy.travels.entity.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yujy
 */
public interface BaseDAO<T, K> {
    /**
     * 保存
     *
     * @param t 泛型
     */
    void save(T t);

    /**
     * @param t
     */
    void update(T t);

    /**
     * @param k
     */
    void delete(K k);

    /**
     * @return
     */
    List<T> findAll();

    /**
     * @param start
     * @param rows
     * @return
     */
    List<T> findByPage(@Param("start") Integer start, @Param("row") Integer rows);

    /**
     * @return
     */
    Integer findTotals();

    /**
     * @param k
     * @return
     */
    T findOne(K k);
}
