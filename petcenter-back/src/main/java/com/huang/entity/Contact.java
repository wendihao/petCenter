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
public class Contact {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String fromname;
    private String toname;
    private Integer from_id;
    private Integer to_id;
    private String msg;
    private String portrait;
    private Integer status;

}
