package com.cyz.dao;

import com.cyz.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

    User findByUserNameAndPassword(String userName,String password);
}
