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
public class XunWu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer varieties;
    private String address;
    private String introduce;
    private String images;
    private Date time;
    private String datetime;
    private String success;
    private Integer userId;
    private Integer schedule;
    private String uuid;
    private User user;
}
