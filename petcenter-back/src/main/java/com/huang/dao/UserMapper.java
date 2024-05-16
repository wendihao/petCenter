package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.User;
import com.huang.entity.UserUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    Integer queryUser(String username);
    List<User> queryAllById(Integer id);
    User queryUserById(Integer id);
    User queryUserByUserName(String username);
    List<User> queryAllByUserName(String username);
    int updateUserById(User user);
    Integer saveUser(User user);
    User queryUserByName(String name);
    IPage<User> queryAllUser(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
    int deleteUserById(String id);
    int updateStatusById(Integer id);
    int updateStatusById1(Integer id);
}
