package com.yujy.travels.dao;

import com.yujy.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceDAO extends BaseDAO<Province, String> {
    Province findOne(String id);
}
