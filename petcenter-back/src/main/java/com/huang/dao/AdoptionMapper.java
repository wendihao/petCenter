package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Adoption;
import com.huang.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdoptionMapper extends BaseMapper<Adoption> {
    int insertAdoption(Adoption adoption);
    Adoption queryAdoptionByBlogIdAndName(@Param("blogId") Integer blogId,@Param("name") String name);
    List<Adoption> querryAdoptionByUserId(Integer userId);
    List<Adoption> querryAdoptionByAdopterId(Integer adopterId);
    List<Adoption> querryAllAdoption();
    Integer updateAdoption(Adoption adoption);
    List<Adoption> querryAdoptionByAdopterId1(Integer adopterId);
    Adoption querryAdoption(@Param("blogId") Integer blogId, @Param("adopterId")Integer adopterId);
    Adoption querryAdoptionByBlogId(Integer blogId);
   List<Integer> querryAdoptionBySuccess(String userId);
    IPage<Adoption> queryBlogPage(IPage<Adoption> page, @Param(Constants.WRAPPER) Wrapper<Adoption> queryWrapper);
    int deleteAdoptionById(Integer id);
}
