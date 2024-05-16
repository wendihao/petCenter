package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.entity.Admin;
import com.huang.entity.JuBao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JuBaoMapper extends BaseMapper<JuBao> {
    int deleteJuBaoById(Integer id);
    Integer insertJuBao(JuBao juBao);
    IPage<JuBao> querryAllJuBao(Page<JuBao> page);
    JuBao queryJuBaoById(Integer id);
}
