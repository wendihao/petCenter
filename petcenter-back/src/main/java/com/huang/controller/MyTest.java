package com.huang.controller;

import com.huang.dao.UserMapper;
import com.huang.entity.Blog;
import com.huang.entity.HomeLessPet;
import com.huang.entity.User;
import com.huang.entity.XunWu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MyTest {
    /*@Autowired
    private UserService userService;*/
    @Autowired
    private UserMapper userMapper;
    public static MyTest myTest;

    @PostConstruct
    public void init() {
        myTest = this;
    }

    public static User test(String username) {
        return myTest.userMapper.queryUserByUserName(username);
    }
}
