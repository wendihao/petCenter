package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
   int insertComment(Comment comment);
   List<Comment> querryById(Integer id);
   IPage<Comment> querryAllComment(IPage<Comment> page, @Param(Constants.WRAPPER) Wrapper<Comment> queryWrapper);
    int deleteCommentById(Integer id);
    void updateCommentByUserId(@Param("portrait") String portrait, @Param("fromname") String fromname,@Param("user_id") Integer user_id);
}
