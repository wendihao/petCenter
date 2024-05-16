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
public class HomeLessPet {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer varieties;
    private String address;
    private String introduce;
    private String images;
    private Date time;
    private String datetime;
    private Integer userId;
    private Integer schedule;
    private String uuid;
    private Integer petrenling;
    private User user;
    private Integer renlingId;
}
