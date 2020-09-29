package com.wold.shiro;

import com.wold.shiro.pojo.User;
import com.wold.shiro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {

    @Autowired
    private UserService service;

    @Test
    void contextLoads() {
        User user=service.getUserByName("zhang");
        System.out.println(user);
    }

}
