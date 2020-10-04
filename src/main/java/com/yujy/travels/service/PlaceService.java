package com.yujy.travels.service;

import com.yujy.travels.entity.Place;

import java.util.List;

/**
 * @author yujy
 */
public interface PlaceService {


    /**
     * 删除景点
     * @param placeId
     */
    void deletePlace(String placeId);

    /**
     * 更新景点信息
     *
     * @param place
     */
    void updatePlaceInfo(Place place);

    /**
     * 添加景点信息
     *
     * @param place
     */
    void savePlace(Place place);

    /**
     * 根据省份id查询景点信息
     *
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId);

    /**
     * 根据省份id查询景点个数
     *
     * @param provinceId
     * @return
     */
    Integer findByProvinceIdCounts(String provinceId);

    /**
     * 根据id返回景点信息
     * @param placeId
     * @return
     */
    Place findOne(String placeId);
}
