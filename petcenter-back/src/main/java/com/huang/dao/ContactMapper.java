package com.huang.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.huang.entity.Contact;
import com.huang.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContactMapper extends BaseMapper<Contact> {
    int insertContact(Contact contact);
    List<Contact> findAllContact(@Param("fromname") String fromname, @Param("toname") String toname);
    List<Contact> findAllContactByToName(String toname);
    List<Contact> findAllContactByToName1(String toname);
   List<Contact> findAllContactByToName2(@Param("toname") String toname, @Param("fromname") String username);
    void updateContact(@Param("toname") String toname,@Param("fromname") String fromname);
    void updateContactByUserId(@Param("portrait") String portrait, @Param("fromname") String fromname,@Param("user_id") Integer user_id);
    void updateContactByUserId1(@Param("fromname") String fromname,@Param("user_id") Integer user_id);
}
