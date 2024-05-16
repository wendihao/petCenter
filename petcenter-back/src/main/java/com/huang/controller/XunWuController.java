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
public class XunWuController {
    @Autowired
    private XunWuMapper xunWuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private UploadMapper uploadMapper;

    @PostMapping("/save2")
    public String saveXunWu(@RequestBody XunWu xunWu) {
        String uuid = UUID.randomUUID().toString();
        xunWu.setUuid(uuid);
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        int insert1 = xunWuMapper.insertXunWu(xunWu);
        UserUpload userUpload = new UserUpload();
        Upload upload = new Upload();
        userUpload.setUserId(xunWu.getUserId());
        upload.setUserId(xunWu.getUserId());
        User user = userMapper.queryUserById(xunWu.getUserId());
        userUpload.setUsername(user.getName());
        upload.setUsername(user.getName());
        userUpload.setAnimalId(uuid);
        upload.setAnimalId(uuid);
        if (xunWu.getVarieties() == 0) {
            userUpload.setVarieties("狗");
            upload.setVarieties("狗");
        } else if (xunWu.getVarieties() == 1) {
            userUpload.setVarieties("猫");
            upload.setVarieties("猫");
        } else {
            userUpload.setVarieties("其他");
            upload.setVarieties("其他");
        }
        userUpload.setCategory("寻物启事");
        upload.setCategory("寻物启事");
        userUpload.setImages(xunWu.getImages());
        upload.setImages(xunWu.getImages());
        userUpload.setTime(date1);
        upload.setTime(date1);
        userUpload.setIntroduce(xunWu.getIntroduce());
        upload.setIntroduce(xunWu.getIntroduce());
        upload.setSuccess(0);
        int insert2 = userUploadMapper.saveUserUpload(userUpload);
        int insert3 = uploadMapper.saveUpload(upload);
        if (insert1 > 0 && insert2 > 0 && insert3 > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/findAllXunWu/{current}/{size}")
    public IPage<XunWu> selectAll(@PathVariable("current") Integer current,
                                        @PathVariable("size") Integer size){
        Page<XunWu> page = new Page<>(current,size);
        IPage<XunWu> mapIPage = xunWuMapper.selectXunWu(page);
        List<XunWu> records = mapIPage.getRecords();
        for (XunWu record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return mapIPage;
    }
    @GetMapping("/findByAddress2/{current}/{size}/{address}")
    public IPage<XunWu> selectByUser3(@PathVariable("current") Integer current,
                                            @PathVariable("size") Integer size,
                                            @PathVariable("address") String address){
        QueryWrapper<XunWu> wrapper = new QueryWrapper();
        wrapper.like("address", address);
        wrapper.eq("schedule",1);
        Page<XunWu> page = new Page<>(current,size);
        IPage<XunWu> xunWuIPage = xunWuMapper.queryXunWuPage(page, wrapper);
        List<XunWu> records = xunWuIPage.getRecords();
        for (XunWu record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return xunWuIPage;
    }
    @GetMapping("/findXunwu/{id}")
    public XunWu selectXunWu(@PathVariable("id") Integer id){
        XunWu xunWu = xunWuMapper.queryXunWuById(id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        xunWu.setDatetime(formatter.format(xunWu.getTime()));
        return xunWu;
    }
}
