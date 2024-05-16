package com.huang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.AdoptionMapper;
import com.huang.dao.BlogMapper;
import com.huang.dao.PetBlogMapper;
import com.huang.dao.UserMapper;
import com.huang.entity.Adoption;
import com.huang.entity.Blog;
import com.huang.entity.PetBlog;
import com.huang.entity.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootTest
class PetcenterBackApplicationTests {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PetBlogMapper petBlogMapper;
    @Autowired
    AdoptionMapper adoptionMapper;
    @Test
    void contextLoads() {
//        Date date = new Date();
//        java.sql.Date date1 = new java.sql.Date(date.getTime());
//        Blog blog = new Blog(11,"测试","测试", null, "ceshi", 1, 1, "ceshi", 1, 1, date1, 1,null);
////        User user = new User(3,"老连",0,"惠州","123123123123","123123123@qq.com","laohuang","123123",null,null);
//        PetBlog petBlog = new PetBlog(3,"测试",null,1,"测试",null);
//        Adoption adoption = new Adoption(1,"测试", "cs", 123, "123", "123", 123, "123", 1, null);
        Page<Blog> page = new Page<>(1,4);
        IPage<Blog> blogIPage = blogMapper.selectMyBlog(page);
        List<Blog> records = blogIPage.getRecords();
        for (Blog record : records) {
            //System.out.println(record);
        }


    }
}
