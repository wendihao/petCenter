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
public class BlogController {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private AdoptionMapper adoptionMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UploadMapper uploadMapper;
    @GetMapping ("/findAll")
    public List<Blog> selectAll(){
        List<Blog> blogs = blogMapper.queryAllBlog();
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }
     @GetMapping ("/findmyadoption/{adopterId}")
    public List<Blog> findmyadoption(@PathVariable("adopterId") Integer adopterId){
        List<Blog> blogs = blogMapper.queryAllBlogByAdopterId(adopterId);
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }
    @GetMapping ("/findmysongyang/{userId}")
    public List<Blog> findmysongyang(@PathVariable("userId") Integer userId){
        List<Blog> blogs = blogMapper.queryAllBlogByuserId(userId);
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }
    @GetMapping ("/getadoptionnum/{Id}")
    public Integer getadoptionnum(@PathVariable("Id") Integer Id){
        List<Blog> blogs = blogMapper.getAdoptionNumByAdopterId(Id);
        return blogs.size();
    }
    @GetMapping ("/getsongyangnum/{Id}")
    public Integer getsongyangnum(@PathVariable("Id") Integer Id){
        List<Blog> blogs = blogMapper.getSongYangNumByUserId(Id);
        return blogs.size();
    }
    @GetMapping ("/findByUserId/{Id}")
    public List<Blog> selectByUser(@PathVariable("Id") Integer userid){
        List<Blog> blogs = blogMapper.queryBlogByUserId(userid);
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }
    @GetMapping ("/findByUserId1/{userid}")
    public List<Blog> selectByUser1(@PathVariable("userid") Integer userid){
        List<Blog> blogs = blogMapper.queryBlogByUserId1(userid);
        for (Blog blog : blogs) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
            blog.setDatetime(formatter.format(blog.getTime()));
        }
        return blogs;
    }
    @GetMapping ("/findBlog/{blogid}")
    public Blog selectByBlogId(@PathVariable("blogid") Integer blogid){
        Blog blog = blogMapper.queryBlogByBlogId(blogid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        blog.setDatetime(formatter.format(blog.getTime()));
        return blog;
    }
    @GetMapping("/findAll/{current}/{size}")
    public IPage<Blog> selectAll(@PathVariable("current") Integer current,
                                 @PathVariable("size") Integer size){
        Page<Blog> page = new Page<>(current,size);
        IPage<Blog> mapIPage = blogMapper.selectMyBlog(page);
        List<Blog> records = mapIPage.getRecords();
        for (Blog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return mapIPage;
    }
    @GetMapping("findAll/{current}/{size}/{userid}")
    public IPage<Blog> selectAll(@PathVariable("current") Integer current,
                                 @PathVariable("size") Integer size,
                                 @PathVariable("userid") Integer userid){
        QueryWrapper<Blog> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userid);
        Page<Blog> page = new Page<>(current,size);
        IPage<Blog> blogIPage = blogMapper.queryBlogPage(page, wrapper);
        List<Blog> records = blogIPage.getRecords();
        for (Blog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return blogIPage;
    }
    @GetMapping("/findByAddress1/{current}/{size}/{address}")
    public IPage<Blog> selectByAddress(@PathVariable("current") Integer current,
                                            @PathVariable("size") Integer size,
                                            @PathVariable("address") String address){
        QueryWrapper<Blog> wrapper = new QueryWrapper();
        wrapper.like("address", address);
        wrapper.eq("schedule",1);
        Page<Blog> page = new Page<>(current,size);
        IPage<Blog> blogIPage = blogMapper.queryBlogPage(page, wrapper);
        List<Blog> records = blogIPage.getRecords();
        for (Blog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return blogIPage;
    }
    @PostMapping("/save")
    public String saveBlog(@RequestBody Blog blog){
        String uuid = UUID.randomUUID().toString();
        blog.setUuid(uuid);
        blog.setSchedule(0);
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        blog.setTime(date1);
        UserUpload userUpload=new UserUpload();
        Upload upload=new Upload();
        userUpload.setUserId(blog.getUserId());
        upload.setUserId(blog.getUserId());
        User user = userMapper.queryUserById(blog.getUserId());
        userUpload.setUsername(user.getName());
        upload.setUsername(user.getName());
        userUpload.setAnimalId(uuid);
        upload.setAnimalId(uuid);
        if(blog.getVarieties()==0) {
            userUpload.setVarieties("狗");
            upload.setVarieties("狗");
        }else if(blog.getVarieties()==1){
            userUpload.setVarieties("猫");
            upload.setVarieties("猫");
        }else {
            userUpload.setVarieties("其他");
            upload.setVarieties("其他");
        }
        userUpload.setCategory("送养");
        upload.setCategory("送养");
        userUpload.setImages(blog.getImages());
        upload.setImages(blog.getImages());
        userUpload.setTime(date1);
        upload.setTime(date1);
        userUpload.setIntroduce(blog.getIntroduce());
        upload.setIntroduce(blog.getIntroduce());
        upload.setSuccess(0);
        int insert1 = userUploadMapper.saveUserUpload(userUpload);
        int insert2 = blogMapper.saveBlog(blog);
        int insert3 = uploadMapper.saveUpload(upload);
        if (insert1>0&&insert2>0&&insert3>0){
            return "success";
        }else {
            return "error";
        }
    }
    @PostMapping("/blog/update")
    public String updateBlog(@RequestBody Blog blog){
        Integer i = blogMapper.updateBlog(blog);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("blog/setpetadopt/{blogId}/{name}")
    public String setPetadopt(@PathVariable("blogId") Integer blogId ,
                              @PathVariable ("name") String name){
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        User user = userMapper.queryUserByName(name);
        Blog blog = blogMapper.queryBlogById(blogId);
        Adoption adoption = adoptionMapper.queryAdoptionByBlogIdAndName(blogId,name);
        blog.setPetadopt(1);
        blog.setAdopterId(user.getId());
        adoption.setSuccess(1);
        adoption.setTime(date1);
        Integer integer = blogMapper.updateBlog(blog);
        Integer integer1 = adoptionMapper.updateAdoption(adoption);
        if (integer>0&&integer1>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/querry/alladoption1/{current}/{size}/{adopterId}")
    public IPage<Blog> querryBlogByAdopterId(@PathVariable("current")Integer current,
                                             @PathVariable("size")Integer size,
                                             @PathVariable("adopterId")Integer adopterId) {
        QueryWrapper<Blog> wrapper = new QueryWrapper();
        wrapper.eq("adopter_id", adopterId);
        wrapper.eq("schedule", 1);
        Page<Blog> page = new Page<>(current,size);
        IPage<Blog> blogIPage = blogMapper.queryBlogPage(page, wrapper);
        List<Blog> records = blogIPage.getRecords();
        for (Blog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return blogIPage;
    }
    @GetMapping("/querryisexit2/{blogId}/{adopterId}")
    public Boolean querry4(@PathVariable("blogId")Integer blogId,
                           @PathVariable("adopterId")Integer adopterId){
        Blog blog = blogMapper.querryBlog(blogId,adopterId);
        if (blog!=null){
            return false;
        }else {
            return true;
        }
    }
}
