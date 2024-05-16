package com.huang.controller;

import com.huang.dao.MessageMapper;
import com.huang.entity.Adoption;
import com.huang.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class ContactAdminController {
    @Autowired
    private MessageMapper messageMapper;

    @PostMapping("/insert/message")
    public String insertAdoption(@RequestBody Message message){
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        message.setTime(date1);
        int i = messageMapper.insertMessage(message);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
}
