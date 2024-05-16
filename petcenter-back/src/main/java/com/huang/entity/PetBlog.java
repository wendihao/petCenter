package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetBlog {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String introduce; //介绍
    private String images;
    private Integer userId;
    private String title;
    private Date time;
    private String datetime;
    private User user;
    private Integer pageview;
    private Integer comment;
}
