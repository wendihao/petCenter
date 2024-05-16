package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.*;
import com.huang.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
public class RenLingController {
    @Autowired
    private RenLingMapper renLingMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HomeLessPetMapper homeLessPetMapper;
    @PostMapping("/insert/renling")
    public String insertRenling(@RequestBody RenLing renling) {
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        renling.setTime(date1);
        int i = renLingMapper.insertRenling(renling);
        Integer integer = homeLessPetMapper.updateHomelesspet1(renling.getRenlingId(), renling.getHomelesspetId());
        if (i > 0&&integer>0) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/querry/allrenling/{userId}")
    public List<RenLing> querry1(@PathVariable("userId")Integer userId){
        List<RenLing> renlings = renLingMapper.querryRenlingByUserId(userId);
        return renlings;
    }
    @GetMapping("/querry/findrenlinger/{homelesspetId}/{renlingId}")
    public RenLing querry2(@PathVariable("homelesspetId")Integer homelesspetId,
                                      @PathVariable("renlingId")Integer renlingId){
        RenLing renling = renLingMapper.querrRenling(homelesspetId,renlingId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        renling.setDatetime(formatter.format(renling.getTime()));
        return renling;
    }
    @GetMapping("/querryisexit/{homelesspetId}/{renlingId}")
    public Boolean querry3(@PathVariable("homelesspetId")Integer homelesspetId,
                                     @PathVariable("renlingId")Integer renlingId){
        RenLing renling = renLingMapper.querrRenling(homelesspetId,renlingId);
       if (renling!=null){
           return false;
       }else {
           return true;
       }
    }
    @GetMapping("/querry/flag1/{userid}")
    public List<Integer> querry4(@PathVariable("userid") String userId){
        List<Integer> list = renLingMapper.querryAdoptionBySuccess(userId);
        return list;
    }
    @GetMapping("/querry/allrenling/{current}/{size}/{userid}")
    public  IPage<RenLing> querry4(@PathVariable("current") Integer current,
                                    @PathVariable("size") Integer size,
                                    @PathVariable("userid") String userId){
        QueryWrapper<RenLing> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        Page<RenLing> page = new Page<>(current,size);
        IPage<RenLing> renLingIPage = renLingMapper.queryRenlingPage(page, wrapper);
        List<RenLing> records = renLingIPage.getRecords();
        for (RenLing record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return renLingIPage;
    }
    @GetMapping("/delete/renling/{id}")
    public String deleteRenling(@PathVariable("id") Integer id) {
        int i = renLingMapper.deleteRenlingById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
