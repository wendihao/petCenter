package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Blog;
import com.huang.entity.PetBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PetBlogMapper extends BaseMapper<PetBlog> {
    IPage<PetBlog> selectMyPetBlog(IPage<PetBlog> page);
//    List<PetBlog> querryAll();
    PetBlog querryById(Integer id);
    int insertPetBlog(PetBlog petBlog);
    int updatePageView(@Param("id") Integer id,@Param("pageview") Integer pageview);
    int updateComment(@Param("id")Integer id,@Param("comment") Integer comment);
    Integer querryNumberById(Integer id);
    List<PetBlog> querryAll(Integer userId);
    IPage<PetBlog> querryAllpetBlog(IPage<PetBlog> page, @Param(Constants.WRAPPER) Wrapper<PetBlog> queryWrapper);
    PetBlog querrypetBlogById(Integer id);
    Integer updatepetBlogById(PetBlog petBlog);
    IPage<PetBlog> querryAllpetBlog1(Page<PetBlog> page,@Param(Constants.WRAPPER) QueryWrapper<PetBlog> wrapper);
    int deletePetCircleById(Integer id);
    List<PetBlog> querryAllPetCircle();
    List<PetBlog> querrypetBlogByUserId(Integer userId);
}
