package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private int sex;
    private String sex1;
    private String phoneid;
    private String email;
    private String username;
    private String password;
    private String portrait;
}
