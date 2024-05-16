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
public class JuBaoMessage {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer jubaoId;
    private String jubaousername;
    private String reason;
    private String username;
    private String images;
    private String datetime;
    private String feedbackmessage;
}
