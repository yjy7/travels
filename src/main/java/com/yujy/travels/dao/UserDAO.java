package com.yujy.travels.dao;

import com.yujy.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yujy
 */
@Mapper
public interface UserDAO {
    /**
     * 根据用户名返回用户
     * @param username
     * @return
     */
    User findByUserName(String username);


    /**
     * 保存用户
     * @param user
     */
    void save(User user);
}
