package com.yujy.travels.service;

import com.yujy.travels.dao.PlaceDAO;
import com.yujy.travels.dao.ProvinceDAO;
import com.yujy.travels.entity.Place;
import com.yujy.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDAO placeDAO;
    @Autowired
    private ProvinceService provinceService;




    @Override
    public void deletePlace(String placeId) {
        //删除景点 更新省份景点个数
        Place place = placeDAO.findOne(placeId);
        Province province = provinceService.findOne(place.getProvinceId());
        province.setPlaceCounts(province.getPlaceCounts()-1);
        provinceService.updateProvinceInfo(province);

        //删除景点
        placeDAO.delete(placeId);
    }

    @Override
    public void updatePlaceInfo(Place place) {
        placeDAO.update(place);
    }

    @Override
    public void savePlace(Place place) {

        //景点保存
        placeDAO.save(place);
        //查询该景点所属省份信息
        Province province = provinceService.findOne(place.getId());
        //所属省份景点个数更新
        province.setPlaceCounts(province.getPlaceCounts() + 1);
        provinceService.updateProvinceInfo(province);
    }

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page - 1) * rows;
        return placeDAO.findByProvinceIdPage(start, rows, provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {

        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public Place findOne(String placeId) {
        return placeDAO.findOne(placeId);
    }
}
