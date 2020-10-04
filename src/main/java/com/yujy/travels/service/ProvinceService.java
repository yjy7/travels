package com.yujy.travels.service;

import com.yujy.travels.entity.Province;

import java.util.List;

/**
 * @author yujy
 */
public interface ProvinceService {


    /**
     * 根据id更新省份信息
     *
     * @param id
     */
    void updateProvinceInfo(Province province);

    /**
     * 根据id查询省份信息
     *
     * @param id
     * @return
     */
    Province findOne(String id);

    /**
     * 删除省份
     *
     * @param id 省份id
     */
    void delete(String id);

    /**
     * 保存省份的方法
     *
     * @param province 省份
     */
    void save(Province province);

    /**
     * 返回查询页
     *
     * @param page 当前页
     * @param rows 每页显示记录数
     * @return
     */
    List<Province> findByPage(Integer page, Integer rows);

    /**
     * 返回景点数
     *
     * @return
     */
    Integer findTotals();
}
