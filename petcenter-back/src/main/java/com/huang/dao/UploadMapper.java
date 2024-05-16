package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Upload;
import com.huang.entity.UserUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UploadMapper extends BaseMapper<UserUpload> {
    Integer updateByUuid(String animalId);
    IPage<Upload> queryUpload(IPage<Upload> page, @Param(Constants.WRAPPER) Wrapper<Upload> queryWrapper);
    int saveUpload(Upload upload);
    Upload qureyUploadById(Integer id);
    int updateUpload(Upload upload1);
    Upload qureyUploadByAnimalId(String uuid);
    List<Upload> qureyUploadByUserId(Integer userId);
    int deleteUploadById(Integer id);
}
