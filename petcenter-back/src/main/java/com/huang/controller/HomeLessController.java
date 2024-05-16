package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.*;
import com.huang.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class HomeLessController {
    @Autowired
    private HomeLessPetMapper homeLessPetMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RenLingMapper renLingMapper;
    @Autowired
    private UploadMapper uploadMapper;
    @GetMapping("/findByAddress/{current}/{size}/{address}")
    public IPage<HomeLessPet> selectByUser2(@PathVariable("current") Integer current,
                                     @PathVariable("size") Integer size,
                                     @PathVariable("address") String address){
        /*List<Blog> blogs = blogMapper.queryBlogByAddress(address);
        System.out.println(blogs);
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }*/
        QueryWrapper<HomeLessPet> wrapper = new QueryWrapper();
        wrapper.like("address", address);
        wrapper.eq("schedule",1);
        Page<HomeLessPet> page = new Page<>(current,size);
        IPage<HomeLessPet> homeLessPetIPage = homeLessPetMapper.queryHomeLessPetPage(page, wrapper);
        List<HomeLessPet> records = homeLessPetIPage.getRecords();
        for (HomeLessPet record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return homeLessPetIPage;
    }
    @GetMapping("/findByUserId/{current}/{size}/{userId}")
    public IPage<HomeLessPet> selectByUser3(@PathVariable("current") Integer current,
                                            @PathVariable("size") Integer size,
                                            @PathVariable("userId") String userId){
        QueryWrapper<HomeLessPet> wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        Page<HomeLessPet> page = new Page<>(current,size);
        IPage<HomeLessPet> homeLessPetIPage = homeLessPetMapper.queryHomeLessPetPage(page, wrapper);
        List<HomeLessPet> records = homeLessPetIPage.getRecords();
        for (HomeLessPet record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return homeLessPetIPage;
    }
    @GetMapping("/findByRenLingId/{current}/{size}/{renlingId}")
    public IPage<HomeLessPet> selectByUser4(@PathVariable("current") Integer current,
                                            @PathVariable("size") Integer size,
                                            @PathVariable("renlingId") String renlingId){
        QueryWrapper<HomeLessPet> wrapper = new QueryWrapper();
        wrapper.eq("renling_id",renlingId);
        wrapper.eq("schedule", 1);
        Page<HomeLessPet> page = new Page<>(current,size);
        IPage<HomeLessPet> homeLessPetIPage = homeLessPetMapper.queryHomeLessPetPage(page, wrapper);
        List<HomeLessPet> records = homeLessPetIPage.getRecords();
        for (HomeLessPet record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return homeLessPetIPage;
    }
    @GetMapping("/findAllHomelessPet/{current}/{size}")
    public IPage<HomeLessPet> selectAll(@PathVariable("current") Integer current,
                                 @PathVariable("size") Integer size){
        Page<HomeLessPet> page = new Page<>(current,size);
        IPage<HomeLessPet> mapIPage = homeLessPetMapper.selectHomelessPet(page);
        List<HomeLessPet> records = mapIPage.getRecords();
        for (HomeLessPet record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return mapIPage;
    }
    @GetMapping ("/findHomeLessPet/{homelesspetid}")
    public HomeLessPet selectByhomelesspetId(@PathVariable("homelesspetid") Integer homelesspetid) {
        HomeLessPet homeLessPet = homeLessPetMapper.queryHomeLessPetById(homelesspetid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        homeLessPet.setDatetime(formatter.format(homeLessPet.getTime()));
        return homeLessPet;
    }
    @GetMapping ("/findmyzhaoling/{userId}")
    public List<HomeLessPet> findmyzhaoling(@PathVariable("userId") Integer userId) {
       List<HomeLessPet> homeLessPets = homeLessPetMapper.findAllHomelessPetByUserId(userId);
        for (HomeLessPet homeLessPet: homeLessPets) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            homeLessPet.setDatetime(formatter.format(homeLessPet.getTime()));
        }
        return homeLessPets;
    }
    @GetMapping ("/findmyzhaohui/{renlingId}")
    public List<HomeLessPet> findmyzhaohui(@PathVariable("renlingId") Integer renlingId) {
        List<HomeLessPet> homeLessPets = homeLessPetMapper.findAllHomelessPetByRenLingId(renlingId);
        for (HomeLessPet homeLessPet: homeLessPets) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            homeLessPet.setDatetime(formatter.format(homeLessPet.getTime()));
        }
        return homeLessPets;
    }
    @PostMapping("/save1")
    public String saveHomelessPet(@RequestBody HomeLessPet homeLessPet){
        String uuid = UUID.randomUUID().toString();
        homeLessPet.setSchedule(0);
        homeLessPet.setPetrenling(0);
        homeLessPet.setUuid(uuid);
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        homeLessPet.setTime(date1);
        int insert1 = homeLessPetMapper.saveHomelessPet(homeLessPet);
        UserUpload userUpload=new UserUpload();
        Upload upload=new Upload();
        userUpload.setUserId(homeLessPet.getUserId());
        upload.setUserId(homeLessPet.getUserId());
        User user = userMapper.queryUserById(homeLessPet.getUserId());
        userUpload.setUsername(user.getName());
        upload.setUsername(user.getName());
        userUpload.setAnimalId(uuid);
        upload.setAnimalId(uuid);
        if(homeLessPet.getVarieties()==0) {
            userUpload.setVarieties("狗");
            upload.setVarieties("狗");
        }else if(homeLessPet.getVarieties()==1){
            userUpload.setVarieties("猫");
            upload.setVarieties("猫");
        }else {
            userUpload.setVarieties("其他");
            upload.setVarieties("其他");
        }
        userUpload.setCategory("招领");
        upload.setCategory("招领");
        userUpload.setImages(homeLessPet.getImages());
        upload.setImages(homeLessPet.getImages());
        userUpload.setTime(date1);
        upload.setTime(date1);
        userUpload.setIntroduce(homeLessPet.getIntroduce());
        upload.setIntroduce(homeLessPet.getIntroduce());
        upload.setSuccess(0);
        int insert2 = userUploadMapper.saveUserUpload(userUpload);
        int insert3 = uploadMapper.saveUpload(upload);
        if (insert1>0&&insert2>0&&insert3>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/sethomelesspet/{homelesspetId}/{name}")
    public String setPetadopt(@PathVariable("homelesspetId") Integer homelesspetId ,
                              @PathVariable ("name") String name){
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        User user = userMapper.queryUserByName(name);
        HomeLessPet homeLessPet = homeLessPetMapper.queryHomeLessPetById(homelesspetId);
        RenLing renLing = renLingMapper.queryRenlingByHomelessPetIdAndName(homelesspetId,name);
        homeLessPet.setPetrenling(1);
        homeLessPet.setRenlingId(user.getId());
        renLing.setSuccess(1);
        renLing.setTime(date1);
        Integer integer = homeLessPetMapper.updateHomelesspet(homeLessPet);
        Integer integer1 = renLingMapper.updateRenling(renLing);
        if (integer>0&&integer1>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/querryisexit3/{homelesspetId}/{renlingId}")
    public Boolean querry4(@PathVariable("homelesspetId")Integer homelesspetId,
                           @PathVariable("renlingId")Integer renlingId){
        HomeLessPet homeLessPet = homeLessPetMapper.querrHomeLessPet(homelesspetId,renlingId);
        if (homeLessPet!=null){
            return false;
        }else {
            return true;
        }
    }
    @GetMapping ("/getzhaolingnum/{Id}")
    public Integer getzhaolingnum(@PathVariable("Id") Integer Id){
        List<HomeLessPet> homeLessPets = homeLessPetMapper.getZhaoLingNumByUserId(Id);
        return homeLessPets.size();
    }
    @GetMapping ("/getzhaohuinum/{Id}")
    public Integer getzhaohuinum(@PathVariable("Id") Integer Id){
        List<HomeLessPet> homeLessPets = homeLessPetMapper.getZhaoHuiNumByRenLingId(Id);
        return homeLessPets.size();
    }
}
