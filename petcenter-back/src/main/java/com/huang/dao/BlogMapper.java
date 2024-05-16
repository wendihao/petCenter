package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.huang.entity.Blog;
import com.huang.entity.HomeLessPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    IPage<Blog> selectMyBlog(IPage<Blog> page);
    IPage<Blog> queryBlogPage(IPage<Blog> page, @Param(Constants.WRAPPER) Wrapper<Blog> queryWrapper);
    List<Blog> queryAllBlog();
    Blog queryBlogById(Integer id);
    List<Blog> queryBlogByUserId(Integer userid);
    List<Blog> queryBlogByUserId1(Integer userid);
    List<Blog> queryBlogByAddress(String address);
    Blog queryBlogByBlogId(Integer blogid);
    Integer updateBlog(Blog blog);
    Integer saveBlog(Blog blog);
    Integer updateBlogByUuid(String animalId);
    Integer updateBlog1(@Param("adopterId") Integer adopterId,@Param("blogId") Integer blogId);
    Blog querryBlog(@Param("blogId") Integer blogId, @Param("adopterId") Integer adopterId);
    Blog queryBlogByUuid(String animalId);
    int updateBlogByUuid1(Blog blog);
    List<Blog> queryAllSongYang();
    List<Blog> queryAllBlogByAdopterId(Integer adopterId);
    List<Blog> queryAllBlogByuserId(Integer userId);
    List<Blog> getAdoptionNumByAdopterId(Integer Id);
    List<Blog> getSongYangNumByUserId(Integer Id);

    List<Blog> queryAllSongYangByAddress(String address);
}
