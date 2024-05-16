package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.CommentMapper;
import com.huang.dao.PetBlogMapper;
import com.huang.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@ResponseBody
public class PetBlogController {
    @Autowired
    PetBlogMapper petBlogMapper;
    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/petBlog/querryAll/{userId}")
    public List<PetBlog> querryAll(@PathVariable("userId") Integer userId) {
        List<PetBlog> petBlogs = petBlogMapper.querryAll(userId);
        for (PetBlog record : petBlogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return petBlogs;
    }

    @GetMapping("/petBlog/querryAll/{current}/{size}")
    public IPage<PetBlog> querryAll(@PathVariable("current") Integer current,
                                   @PathVariable("size") Integer size) {
        Page<PetBlog> page = new Page<>(current, size);
        IPage<PetBlog> petBlogIPage = petBlogMapper.selectMyPetBlog(page);
        return petBlogIPage;
    }
    @GetMapping("/petBlog/querryById/{id}")
    public PetBlog querryById(@PathVariable("id") Integer id) {
        PetBlog petBlog = petBlogMapper.querryById(id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        petBlog.setDatetime(formatter.format(petBlog.getTime()));
        int pageview = petBlog.getPageview() + 1;
        petBlogMapper.updatePageView(id, pageview);
        return petBlog;
    }

    @PostMapping("/insert")
    public String updateById(@RequestBody Comment comment) {
        PetBlog petBlog = petBlogMapper.querryById(comment.getPetblogId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        comment.setTime(date1);
        petBlog.setDatetime(formatter.format(petBlog.getTime()));
        int comment1 = petBlog.getComment() + 1;
        petBlogMapper.updateComment(comment.getPetblogId(), comment1);
        int i = commentMapper.insertComment(comment);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/comment/querryByPetBlogId/{id}")
    public List<Comment> querryById1(@PathVariable("id") Integer id) {
        List<Comment> commentList = commentMapper.querryById(id);
        return commentList;
    }
    @GetMapping("/comment/querryNumberById/{id}")
    public Integer querryById2(@PathVariable("id") Integer id) {
        Integer number = petBlogMapper.querryNumberById(id);
        return number;
    }
    @PostMapping("/petBlog/insertPetBlog")
    public String insertPetBlog(@RequestBody PetBlog petBlog){
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        petBlog.setTime(date1);
        petBlog.setPageview(0);
        petBlog.setComment(0);
        int i = petBlogMapper.insertPetBlog(petBlog);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/findAllpetBlog/{current}/{size}/{userid}")
    public IPage<PetBlog> querryAllpetBlog(@PathVariable("current") Integer current,
                                    @PathVariable("size") Integer size,
                                           @PathVariable("userid") String userId) {
        QueryWrapper<PetBlog> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        Page<PetBlog> page = new Page<>(current,size);
        IPage<PetBlog> petblogIPage = petBlogMapper.querryAllpetBlog(page, wrapper);
        List<PetBlog> records = petblogIPage.getRecords();
        for (PetBlog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return petblogIPage;
    }
    @GetMapping("/findpetBlogById/{id}")
    public PetBlog querryById3(@PathVariable("id") Integer id) {
       PetBlog petBlog = petBlogMapper.querrypetBlogById(id);
        return petBlog;
    }
    @GetMapping("/findAllpetBlog/{userid}")
    public List<PetBlog> findAllpetBlog(@PathVariable("userid") Integer userId) {
       List<PetBlog> petBlogs = petBlogMapper.querrypetBlogByUserId(userId);
        for (PetBlog petBlog: petBlogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            petBlog.setDatetime(formatter.format(petBlog.getTime()));
        }
        return petBlogs;
    }
    @PostMapping("/updatepetBlog")
    public String updatepetBlog(@RequestBody PetBlog petBlog) {
         Integer i = petBlogMapper.updatepetBlogById(petBlog);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/findAllComment/{current}/{size}")
    public IPage<Comment> querryAllComment(@PathVariable("current") Integer current,
                                           @PathVariable("size") Integer size) {
        QueryWrapper<Comment> wrapper = new QueryWrapper();
        Page<Comment> page = new Page<>(current, size);
        IPage<Comment> commentIPage = commentMapper.querryAllComment(page, wrapper);
        List<Comment> records = commentIPage.getRecords();
        for (Comment record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return commentIPage;
    }
    @GetMapping("/querryAllPetCircle")
    public List<PetBlog> querryAllPetCircle() {
        List<PetBlog> petBlogs = petBlogMapper.querryAllPetCircle();
        for (PetBlog petBlog :petBlogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            petBlog.setDatetime(formatter.format(petBlog.getTime()));
        }
        return petBlogs;
    }
    @GetMapping("/petblog/delete/{id}")
    public String deletePetblogById(@PathVariable("id") Integer id) {
        int i = petBlogMapper. deletePetCircleById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
