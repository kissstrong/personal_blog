package com.cyz.service.impl;

import com.cyz.dao.UserDao;
import com.cyz.po.User;
import com.cyz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String userName, String password) {
        User user=userDao.findByUserNameAndPassword(userName,password);
        return user;
    }
}
