package com.huang.service;

import com.huang.dao.UserMapper;
import com.huang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    //发送简单邮件
    public User queryUserByUserName(String username)
    {
        User user = userMapper.queryUserByUserName(username);
        return user;
    }
}