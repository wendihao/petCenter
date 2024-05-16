package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Blog;
import com.huang.entity.HomeLessPet;
import com.huang.entity.UserUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserUploadMapper extends BaseMapper<UserUpload> {
    Integer saveUserUpload(UserUpload userUpload);
    //IPage<UserUpload> queryUserUpload(Page<UserUpload> page, QueryWrapper<UserUpload> wrapper);
    IPage<UserUpload> queryUserUpload(IPage<UserUpload> page, @Param(Constants.WRAPPER) Wrapper<UserUpload> queryWrapper);
    UserUpload queryUploadById(Integer id);
    Integer deleteUploadById(Integer id);
}
