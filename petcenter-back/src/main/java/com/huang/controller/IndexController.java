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
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private HomeLessPetMapper homeLessPetMapper;
    @Autowired
    private UploadMapper uploadMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private XunWuMapper xunWuMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PetBlogMapper petBlogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private JuBaoMapper juBaoMapper;

    @GetMapping("/index/select/{type}")
    public List<Index> selectByType1(@PathVariable("type") String type) {
        List<Index> list = new ArrayList<>();
        if (type.equals("寻物启事")) {
            List<XunWu> xunwus = xunWuMapper.queryAllXunWu();
            for (XunWu xunwu : xunwus) {
                Index index = new Index();
                index.setAddress(xunwu.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(xunwu.getTime()));
                index.setId(xunwu.getId());
                index.setImages(xunwu.getImages());
                index.setTitle(xunwu.getIntroduce());
                index.setUserId(xunwu.getUserId());
                index.setUuid(xunwu.getUuid());
                list.add(index);
            }
            return list;
        }
        if (type.equals("宠物招领")) {
            List<HomeLessPet> zhaolings = homeLessPetMapper.queryAllZhaoLing();
            for (HomeLessPet zhaoling : zhaolings) {
                Index index = new Index();
                index.setAddress(zhaoling.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(zhaoling.getTime()));
                index.setId(zhaoling.getId());
                index.setImages(zhaoling.getImages());
                index.setTitle(zhaoling.getTitle());
                index.setUserId(zhaoling.getUserId());
                index.setStatus(zhaoling.getPetrenling());
                index.setUuid(zhaoling.getUuid());
                list.add(index);
            }
            return list;
        } else {
            List<Blog> songYangs = blogMapper.queryAllSongYang();
            for (Blog blog : songYangs) {
                Index index = new Index();
                index.setAddress(blog.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(blog.getTime()));
                index.setId(blog.getId());
                index.setImages(blog.getImages());
                index.setTitle(blog.getTitle());
                index.setUserId(blog.getUserId());
                index.setStatus(blog.getPetadopt());
                index.setUuid(blog.getUuid());
                list.add(index);
            }
            //System.out.println(list);
            return list;
        }
    }
    @GetMapping("/findByAddress/{type}/{value}")
    public List<Index> selectByType2(@PathVariable("type") String type,@PathVariable("value") String address) {
        //System.out.println(type);
       // System.out.println(address);
        List<Index> list = new ArrayList<>();
        if (type.equals("寻物启事")) {
            List<XunWu> xunwus = xunWuMapper.queryAllXunWuByAddress("%"+address+"%");
            for (XunWu xunwu : xunwus) {
                Index index = new Index();
                index.setAddress(xunwu.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(xunwu.getTime()));
                index.setId(xunwu.getId());
                index.setImages(xunwu.getImages());
                index.setTitle(xunwu.getIntroduce());
                index.setUserId(xunwu.getUserId());
                index.setUuid(xunwu.getUuid());
                list.add(index);
            }
           // System.out.println(list);
            return list;
        }
        if (type.equals("宠物招领")) {
            List<HomeLessPet> zhaolings = homeLessPetMapper.queryAllZhaoLingByAddress("%"+address+"%");
            for (HomeLessPet zhaoling : zhaolings) {
                Index index = new Index();
                index.setAddress(zhaoling.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(zhaoling.getTime()));
                index.setId(zhaoling.getId());
                index.setImages(zhaoling.getImages());
                index.setTitle(zhaoling.getTitle());
                index.setUserId(zhaoling.getUserId());
                index.setStatus(zhaoling.getPetrenling());
                index.setUuid(zhaoling.getUuid());
                list.add(index);
            }
            return list;
        } else {
            List<Blog> songYangs = blogMapper.queryAllSongYangByAddress("%"+address+"%");
            for (Blog blog : songYangs) {
                Index index = new Index();
                index.setAddress(blog.getAddress());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                index.setDatetime(formatter.format(blog.getTime()));
                index.setId(blog.getId());
                index.setImages(blog.getImages());
                index.setTitle(blog.getTitle());
                index.setUserId(blog.getUserId());
                index.setStatus(blog.getPetadopt());
                index.setUuid(blog.getUuid());
                list.add(index);
            }
            return list;
        }
    }
}



