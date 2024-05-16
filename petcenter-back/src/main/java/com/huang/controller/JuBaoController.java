package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.*;
import com.huang.entity.*;
import com.huang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class JuBaoController {
    @Autowired
    private JuBaoMapper juBaoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @PostMapping("/jubao/insert")
    public String updatepetBlog(@RequestBody JuBao juBao) {
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        juBao.setTime(date1);
        if(juBao.getReason().equals("1")){
            juBao.setReason("侮辱谩骂");
        }
        if(juBao.getReason().equals("2")){
            juBao.setReason("违法犯罪");
        }
        if(juBao.getReason().equals("3")){
            juBao.setReason("不实信息");
        }
        if(juBao.getReason().equals("4")){
            juBao.setReason("涉嫌欺诈");
        }
        if(juBao.getReason().equals("5")){
            juBao.setReason("其他");
        }
        Integer i = juBaoMapper.insertJuBao(juBao);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/findAllJuBao/{current}/{size}")
    public IPage<JuBao> querryAllComment(@PathVariable("current") Integer current,
                                           @PathVariable("size") Integer size) {
        Page<JuBao> page = new Page<>(current, size);
        IPage<JuBao> juBaoIPage = juBaoMapper.querryAllJuBao(page);
        List<JuBao> records = juBaoIPage.getRecords();
        for (JuBao record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return juBaoIPage;
    }
    @GetMapping ("/findJuBaoById/{id}")
    public JuBao selectByhomelesspetId(@PathVariable("id") Integer id) {
        JuBao juBao = juBaoMapper.queryJuBaoById(id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        juBao.setDatetime(formatter.format(juBao.getTime()));
        return juBao;
    }
}
