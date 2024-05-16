package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.HomeLessPet;
import com.huang.entity.XunWu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface XunWuMapper extends BaseMapper<XunWu> {
    int insertXunWu(XunWu xunWu);
   Integer updateXunWuByUuid(String animalId);
    IPage<XunWu> selectXunWu(Page<XunWu> page);
    IPage<XunWu> queryXunWuPage(IPage<XunWu> page, @Param(Constants.WRAPPER) Wrapper<XunWu> queryWrapper);
    XunWu queryXunWuById(Integer id);
    XunWu queryXunWuByUuid(String animalId);
    int updateXunWuByUuid1(XunWu xunWu);
    List<XunWu> queryAllXunWu();
    List<XunWu> queryAllXunWuByAddress(String address);
}
