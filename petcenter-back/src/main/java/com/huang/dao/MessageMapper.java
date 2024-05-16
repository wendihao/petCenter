package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Adoption;
import com.huang.entity.Message;
import com.huang.entity.Upload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message> {
    int insertMessage(Message message);
    IPage<Message> queryAllMessage(IPage<Message> page, @Param(Constants.WRAPPER) Wrapper<Message> queryWrapper);
    Message queryMessageById(Integer messageid);
    int deleteMessageById(Integer id);
    void updateMessage(@Param("message1") String message1 ,@Param("name") String name,@Param("feedbackmessage") String feedbackmessage);
}
