package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Blog;
import com.huang.entity.HomeLessPet;
import com.huang.entity.RenLing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeLessPetMapper extends BaseMapper<HomeLessPet> {
    IPage<HomeLessPet>queryHomeLessPetPage(IPage<HomeLessPet> page, @Param(Constants.WRAPPER) Wrapper<HomeLessPet> queryWrapper);
    IPage<HomeLessPet> selectHomelessPet(Page<HomeLessPet> page);
    int saveHomelessPet(HomeLessPet homeLessPet);
    Integer updateHomelessPetByUuid(String animalId);
    HomeLessPet queryHomeLessPetById(Integer id);
    Integer updateHomelesspet(HomeLessPet homeLessPet);
    Integer updateHomelesspet1(@Param("renlingId") Integer renlingId,@Param("homelesspetId") Integer homelesspetId);
    HomeLessPet querrHomeLessPet(@Param("homelesspetId") Integer homelesspetId, @Param("renlingId") Integer renlingId);
    HomeLessPet querrHomeLessPetByUuid(String animalId);
    int updateHomelessPetByUuid1(HomeLessPet homeLessPet);
    List<HomeLessPet> queryAllZhaoLing();
    List<HomeLessPet> findAllHomelessPetByUserId(Integer userId);
    List<HomeLessPet> findAllHomelessPetByRenLingId(Integer renlingId);
    List<HomeLessPet> getZhaoLingNumByUserId(Integer Id);
    List<HomeLessPet> getZhaoHuiNumByRenLingId(Integer Id);

    List<HomeLessPet> queryAllZhaoLingByAddress(String address);
}
