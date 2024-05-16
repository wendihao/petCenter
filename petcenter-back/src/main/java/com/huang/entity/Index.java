package com.huang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Index {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String images;
    private String address;
    private String datetime;
    private Data time;
    private String title;
    private Integer userId;
    private Integer status;
    private String uuid;

}
