package com.yujy.travels.service;

import com.yujy.travels.entity.User;

/**
 * @author yujy
 */
public interface UserService {

    User login(User user);
    /**
     * 注册
     * @param user
     */
    void register(User user);

}
