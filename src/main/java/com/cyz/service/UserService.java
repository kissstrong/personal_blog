package com.cyz.service;

import com.cyz.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
