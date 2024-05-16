package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Adoption;
import com.huang.entity.Blog;
import com.huang.entity.RenLing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RenLingMapper extends BaseMapper<Adoption> {
    int insertRenling(RenLing renLing);
    List<RenLing> querryRenlingByUserId(Integer userId);
    RenLing queryRenlingByHomelessPetIdAndName(@Param("homelesspetId") Integer homelesspetId,@Param("name") String name);
    Integer updateRenling(RenLing renLing);
    RenLing querrRenling(@Param("homelesspetId") Integer homelesspetId, @Param("renlingId") Integer renlingId);
    List<Integer> querryAdoptionBySuccess(String userId);
    IPage<RenLing> queryRenlingPage(IPage<RenLing> page, @Param(Constants.WRAPPER) Wrapper<RenLing> queryWrapper);
    int deleteRenlingById(Integer id);
}
