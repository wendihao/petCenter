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
public class JuBao {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String reason;
    private String images;
    private Date time;
    private String datetime;
    private Integer jubaoId;
    private String jubaousername;
}
