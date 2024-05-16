package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.dao.*;
import com.huang.entity.*;
import com.huang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserUploadMapper userUploadMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private HomeLessPetMapper homeLessPetMapper;
    @Autowired
    private UploadMapper uploadMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private XunWuMapper xunWuMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ContactMapper contactMapper;


    @GetMapping("/login/{username}/{password}")
    public int userLogin(@PathVariable("username") String username,
                         @PathVariable("password") String password
    ) {
        Integer id = userMapper.queryUser(username);
        ;
        if (id != null) {
            User user = userMapper.queryUserById(id);
            if (user.getPassword().equals(password) && user.getStatus() != 1) {
                return 200;
            } else if (user.getPassword().equals(password) && user.getStatus() == 1) {
                return 401;
            } else {
                return 405;//密码错误
            }
        } else {
            return 404;//用户错误
        }
    }

    @GetMapping("/login1/{phoneid}/{email}/{username}")
    public int userLogin(@PathVariable("phoneid") String phoneid,
                         @PathVariable("email") String email,
                         @PathVariable("username") String username

    ) {
        Integer id = userMapper.queryUser(username);
        if (id != null) {
            User user = userMapper.queryUserById(id);
            if (user.getPhoneid().equals(phoneid) && user.getEmail().equals(email)) {
                return 200;
            } else {
                return 405;//手机号和邮箱出错
            }
        } else {
            return 404;//用户错误
        }
    }

    @GetMapping("/user/select/{userName}")
    public User userSelectByUserName(@PathVariable("userName") String userName) {
        User user = userMapper.queryUserByUserName(userName);
        return user;
    }

    @GetMapping("/user/delete/{id}")
    public String userDeleteById(@PathVariable("id") String id) {
        int i = userMapper.deleteUserById(id);
        if (i > 0) {
            return "success";
        } else {
            return "fales";
        }
    }

    @GetMapping("/user/selectBlog/{userName}")
    public List<User> userSelectBlogByUserName(@PathVariable("userName") String userName) {
        List<User> users = userMapper.queryAllByUserName(userName);
        return users;
    }

    @GetMapping("/user/selectbyid/{id}")
    public User querryUserById(@PathVariable("id") Integer id) {
        User user = userMapper.queryUserById(id);
        return user;
    }

    @PostMapping("/user/update")
    public String userUpdate(@RequestBody User user) {
        String portrait = user.getPortrait();
        String fromname = user.getUsername();
        Integer user_id = user.getId();
        commentMapper.updateCommentByUserId(portrait,fromname,user_id);
        contactMapper.updateContactByUserId(portrait,fromname,user_id);
        contactMapper.updateContactByUserId1(fromname,user_id);
        int i = userMapper.updateUserById(user);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/user/save")
    public String saveUser(@RequestBody User user) {
        user.setStatus(0);
        user.setPortrait("http://119.29.250.12:8081/myimg/moren.jpg");
        try {
            User user1 = userMapper.queryUserByUserName(user.getUsername());
            if(user1!=null){
                return "error";
            }else {
                userMapper.saveUser(user);
                return "success";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "error1";
    }

    @GetMapping("findAll3/{current}/{size}/{id}")
    public IPage<Upload> selectAll1(@PathVariable("current") Integer current,
                                    @PathVariable("size") Integer size,
                                    @PathVariable("id") Integer id
    ) {
        QueryWrapper<Upload> wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        Page<Upload> page = new Page<>(current, size);
        IPage<Upload> uploadIPage = uploadMapper.queryUpload(page, wrapper);
        List<Upload> records = uploadIPage.getRecords();
        for (Upload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return uploadIPage;
    }
    @GetMapping("findAll3/{userid}")
    public List<Upload> selectAll(@PathVariable("userid") Integer userId) {
       List<Upload> uploads = uploadMapper.qureyUploadByUserId(userId);
        for (Upload record : uploads) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return uploads;
    }

    @PostMapping("/update1/{id}")
    public String update(@PathVariable("id") Integer id,
                         @RequestBody Blog blog) {
        Upload upload1 = new Upload();
        upload1.setAnimalId(blog.getUuid());
        upload1.setIntroduce(blog.getIntroduce());
        upload1.setImages(blog.getImages());
        if (blog.getVarieties() == 0) {
            upload1.setVarieties("狗");
        }
        if (blog.getVarieties() == 1) {
            upload1.setVarieties("猫");
        }
        if (blog.getVarieties() == 2) {
            upload1.setVarieties("其他");
        }
        int i = blogMapper.updateBlogByUuid1(blog);
        int i1 = uploadMapper.updateUpload(upload1);

        if (i > 0 && i1 > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/update2/{id}")
    public String update2(@PathVariable("id") Integer id,
                          @RequestBody HomeLessPet homeLessPet) {
        Upload upload1 = new Upload();
        upload1.setAnimalId(homeLessPet.getUuid());
        upload1.setIntroduce(homeLessPet.getIntroduce());
        upload1.setImages(homeLessPet.getImages());
        if (homeLessPet.getVarieties() == 0) {
            upload1.setVarieties("狗");
        }
        if (homeLessPet.getVarieties() == 1) {
            upload1.setVarieties("猫");
        }
        if (homeLessPet.getVarieties() == 2) {
            upload1.setVarieties("其他");
        }
        int i = homeLessPetMapper.updateHomelessPetByUuid1(homeLessPet);
        int i1 = uploadMapper.updateUpload(upload1);

        if (i > 0 && i1 > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/update3/{id}")
    public String update3(@PathVariable("id") Integer id,
                          @RequestBody XunWu xunWu) {
        Upload upload1 = new Upload();
        upload1.setAnimalId(xunWu.getUuid());
        upload1.setIntroduce(xunWu.getIntroduce());
        upload1.setImages(xunWu.getImages());
        if (xunWu.getVarieties() == 0) {
            upload1.setVarieties("狗");
        }
        if (xunWu.getVarieties() == 1) {
            upload1.setVarieties("猫");
        }
        if (xunWu.getVarieties() == 2) {
            upload1.setVarieties("其他");
        }
        int i = xunWuMapper.updateXunWuByUuid1(xunWu);
        int i1 = uploadMapper.updateUpload(upload1);

        if (i > 0 && i1 > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/findStatusByUsername/{userName}")
    public String findStatusByUsername(@PathVariable("userName") String userName) {
        if(userName.equals("undefined")){
            return "true";
        }
        User user = userMapper.queryUserByUserName(userName);
        if (user.getStatus() == 1) {
            return "false";
        } else
            return "true";
    }
}

