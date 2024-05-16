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
public class Adoption {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String phoneId;
    private String wechatId;
    private String email;
    private String qqId;
    private String blogtitle;
    private String condit;
    private Integer blogId;
    private Integer userId;
    private Integer adopterId;
    private User user;
    private Integer success;
    private Date time;
    private String datetime;
}
