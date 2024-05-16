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
public class Comment {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer petblogId;
    private String comment;
    private String username;
    private Integer user_id;
    private String portrait;
    private Date time;
    private String datetime;
}
