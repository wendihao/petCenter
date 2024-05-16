package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.huang.entity.Admin;
import com.huang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    Integer queryAdmin(String username);
    Admin queryAdminById(Integer id);
    Admin queryAdminByUserName(String username);
    Integer insertAdmin(Admin admin);
}
