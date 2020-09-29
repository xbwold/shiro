package com.wold.shiro.service.impl;

import com.wold.shiro.dao.UserDao;
import com.wold.shiro.pojo.User;
import com.wold.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }
}
