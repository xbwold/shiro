package com.wold.shiro.service;

import com.wold.shiro.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User getUserByName(String name);
}
