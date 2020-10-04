package com.yujy.travels.service;

import com.yujy.travels.dao.ProvinceDAO;
import com.yujy.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yujy
 */

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public void updateProvinceInfo(Province province) {
        provinceDAO.update(province);
    }

    @Override
    public Province findOne(String id) {
        return provinceDAO.findOne(id);
    }

    @Override
    public void delete(String id) {
        provinceDAO.delete(id);
    }

    @Override
    public void save(Province province) {
        province.setPlaceCounts(0);//刚添加的省份景点个数为0
        provinceDAO.save(province);
    }

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        return provinceDAO.findByPage(start, rows);
    }

    @Override
    public Integer findTotals() {
        return provinceDAO.findTotals();
    }
}
