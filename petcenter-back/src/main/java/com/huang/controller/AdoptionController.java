package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.AdoptionMapper;
import com.huang.dao.BlogMapper;
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
public class AdoptionController {
    @Autowired
    AdoptionMapper adoptionMapper;
    @Autowired
    BlogMapper blogMapper;

    @PostMapping("/insert/adoption")
    public String insertAdoption(@RequestBody Adoption adoption) {
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        adoption.setTime(date1);
        adoption.setSuccess(0);
        int i = adoptionMapper.insertAdoption(adoption);
        Integer integer = blogMapper.updateBlog1(adoption.getAdopterId(), adoption.getBlogId());
        if (i > 0 && integer > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete/adoption/{id}")
    public String deleteAdoption(@PathVariable("id") Integer id) {
        int i = adoptionMapper.deleteAdoptionById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/querry/alladoption/{userId}")
    public List<Adoption> querryAllAdoption(@PathVariable("userId") Integer userId) {
        List<Adoption> adoptions = adoptionMapper.querryAdoptionByUserId(userId);
        return adoptions;
    }

    @GetMapping("/querry/alladoption1/{adopterId}")
    public List<Adoption> querryAllAdoptio1(@PathVariable("adopterId") Integer adopterId) {
        List<Adoption> adoptions = adoptionMapper.querryAdoptionByAdopterId(adopterId);
        return adoptions;
    }

    @GetMapping("/querry/findadoption/{blogId}/{adopterId}")
    public Adoption querryAllAdoptio2(@PathVariable("blogId") Integer blogId,
                                      @PathVariable("adopterId") Integer adopterId) {
        Adoption adoption = adoptionMapper.querryAdoption(blogId, adopterId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        adoption.setDatetime(formatter.format(adoption.getTime()));
        return adoption;
    }

    @GetMapping("/findAllAdoption")
    public List<Adoption> querryAllAdoption1() {
        List<Adoption> adoptions = adoptionMapper.querryAllAdoption();
        return adoptions;
    }

    @GetMapping("/querryisexit1/{blogId}/{adopterId}")
    public Boolean querry3(@PathVariable("blogId") Integer blogId,
                           @PathVariable("adopterId") Integer adopterId) {
        Adoption adoption = adoptionMapper.querryAdoption(blogId, adopterId);
        if (adoption != null) {
            return false;
        } else {
            return true;
        }
    }

    @GetMapping("/querry/flag/{userid}")
    public List<Integer> querry4(@PathVariable("userid") String userId) {
        List<Integer> list = adoptionMapper.querryAdoptionBySuccess(userId);
        ;
        return list;
    }

    @GetMapping("/querry/alladoption/{current}/{size}/{userid}")
    public IPage<Adoption> querry4(@PathVariable("current") Integer current,
                                   @PathVariable("size") Integer size,
                                   @PathVariable("userid") String userId) {
        QueryWrapper<Adoption> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        Page<Adoption> page = new Page<>(current, size);
        IPage<Adoption> adoptionIPage = adoptionMapper.queryBlogPage(page, wrapper);
        List<Adoption> records = adoptionIPage.getRecords();
        for (Adoption record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return adoptionIPage;
    }

}

