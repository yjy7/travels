package com.yujy.travels.service;

import com.yujy.travels.dao.UserDAO;
import com.yujy.travels.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yujy
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public User login(User user) {
        User byUserName = userDao.findByUserName(user.getUsername());
        if (byUserName != null) {
            if (byUserName.getPassword().equals(user.getPassword())) {
                return byUserName;
            } else {
                throw new RuntimeException("密码错误");
            }
        } else {
            throw new RuntimeException("用户不存在");
        }
    }

    @Override
    public void register(User user) {
        if (userDao.findByUserName(user.getUsername()) == null) {
            userDao.save(user);
        } else {
            throw new RuntimeException("用户名已存在");
        }
    }
}
