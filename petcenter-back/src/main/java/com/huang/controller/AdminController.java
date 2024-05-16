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

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@ResponseBody
public class AdminController {
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
    private MessageMapper messageMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private XunWuMapper xunWuMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PetBlogMapper petBlogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private JuBaoMapper juBaoMapper;

    @GetMapping("/admin/select/{username}")
    public Admin selectByUsername(@PathVariable("username") String username) {
        Admin admin = adminMapper.queryAdminByUserName(username);
        return admin;
    }
    @GetMapping("/adminlogin/{username}/{password}")
    public int userLogin(@PathVariable("username") String username,
                         @PathVariable("password") String password
    ){
        Integer id = adminMapper.queryAdmin(username);
        if (id!=null){
            Admin admin = adminMapper.queryAdminById(id);
            if (admin.getPassword().equals(password)){
                return 200;
            }else {
                return 405;//密码错误
            }
        }
        else {
            return 404;//用户错误
        }
    }
    @GetMapping("findAll1/{current}/{size}")
    public IPage<UserUpload> selectAll(@PathVariable("current") Integer current,
                                       @PathVariable("size") Integer size) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper();
        Page<UserUpload> page = new Page<>(current, size);
        IPage<UserUpload> userUploadIPage = userUploadMapper.queryUserUpload(page, wrapper);
        List<UserUpload> records = userUploadIPage.getRecords();
        for (UserUpload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return userUploadIPage;
    }

    @GetMapping("findAllMessage/{current}/{size}")
    public IPage<Message> selectAll2(@PathVariable("current") Integer current,
                                     @PathVariable("size") Integer size) {
        QueryWrapper<Message> wrapper = new QueryWrapper();
        Page<Message> page = new Page<>(current, size);
        IPage<Message> messageIPage = messageMapper.queryAllMessage(page, wrapper);
        List<Message> records = messageIPage.getRecords();
        for (Message record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return messageIPage;
    }

    @GetMapping("/findMessage/{messageid}")
    public Message selectByMessageId(@PathVariable("messageid") Integer messageid) {
        Message message = messageMapper.queryMessageById(messageid);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        message.setDatetime(formatter.format(message.getTime()));
        return message;
    }

    @GetMapping("findAll2/{current}/{size}")
    public IPage<Upload> selectAll1(@PathVariable("current") Integer current,
                                    @PathVariable("size") Integer size) {
        QueryWrapper<Upload> wrapper = new QueryWrapper();
        Page<Upload> page = new Page<>(current, size);
        IPage<Upload> uploadIPage = uploadMapper.queryUpload(page, wrapper);
        List<Upload> records = uploadIPage.getRecords();
        for (Upload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return uploadIPage;
    }

    @GetMapping("/findUserUpload/{id}")
    public UserUpload selectBlog(@PathVariable("id") Integer id) {
        UserUpload userUpload = userUploadMapper.queryUploadById(id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
        userUpload.setDatetime(formatter.format(userUpload.getTime()));
        return userUpload;
    }

    @GetMapping("/findUserUpload1/{id}")
    public Blog selectBlog1(@PathVariable("id") Integer id) {
        Upload upload = uploadMapper.qureyUploadById(id);
        Blog blog = blogMapper.queryBlogByUuid(upload.getAnimalId());
        return blog;
    }

    @GetMapping("/findUserUpload2/{id}")
    public HomeLessPet selectBlog2(@PathVariable("id") Integer id) {
        Upload upload = uploadMapper.qureyUploadById(id);
        HomeLessPet homeLessPet = homeLessPetMapper.querrHomeLessPetByUuid(upload.getAnimalId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        homeLessPet.setDatetime(formatter.format(homeLessPet.getTime()));
        return homeLessPet;
    }

    @GetMapping("/findUserUpload3/{id}")
    public XunWu selectBlog3(@PathVariable("id") Integer id) {
        Upload upload = uploadMapper.qureyUploadById(id);
        XunWu xunWu = xunWuMapper.queryXunWuByUuid(upload.getAnimalId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        xunWu.setDatetime(formatter.format(xunWu.getTime()));
        return xunWu;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id) {
        UserUpload userUpload = userUploadMapper.queryUploadById(id);
        Integer i1 = uploadMapper.updateByUuid(userUpload.getAnimalId());
        if (userUpload.getCategory().equals("送养")) {
            Integer i = blogMapper.updateBlogByUuid(userUpload.getAnimalId());
            if (i > 0 && i1 > 0) {
                return "success";
            } else {
                return "error";
            }
        }
        if (userUpload.getCategory().equals("招领")) {
            Integer i = homeLessPetMapper.updateHomelessPetByUuid(userUpload.getAnimalId());
            if (i > 0 && i1 > 0) {
                return "success";
            } else {
                return "error";
            }
        }
        if (userUpload.getCategory().equals("寻物启事")) {
            Integer i = xunWuMapper.updateXunWuByUuid(userUpload.getAnimalId());
            if (i > 0 && i1 > 0) {
                return "success";
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    @GetMapping("/userUpload/delete/{id}")
    public String deleteUpload(@PathVariable("id") Integer id) {
        int i = uploadMapper.deleteUploadById(id);
        int i1 =userUploadMapper.deleteUploadById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/deletePetCircle/{id}")
    public String  deletePetCircle(@PathVariable("id") Integer id) {
        int i = petBlogMapper. deletePetCircleById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/deleteComment/{id}")
    public String  deleteComment(@PathVariable("id") Integer id) {
        int i = commentMapper.deleteCommentById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    @GetMapping("/deleteJuBao/{id}")
    public String  deleteJuBao(@PathVariable("id") Integer id) {
        int i = juBaoMapper.deleteJuBaoById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    @PostMapping("/sendMessage")
    public String updateBlog(@RequestBody Message message) {
        User user = userMapper.queryUserByName(message.getName());
        String message1 = message.getMessage();
        String name = message.getName();
        String feedbackmessage = message.getFeedbackmessage();
        //System.out.println(feedbackmessage);
        messageMapper.updateMessage(message1,name,feedbackmessage);
        try {
            if (user.getSex() == 0) {
                mailService.sandSimpleMail("2960547803@qq.com",
                        message.getEmail(),
                        "2960547803@qq.com",
                        "管理员回复",
                        "尊敬的:" + message.getName() + "女士" + "\n" + "你好,感谢你做出的反馈:" + message.getMessage() + "\n" + message.getFeedbackmessage());
                return "success";
            } else {
                mailService.sandSimpleMail("2960547803@qq.com",
                        message.getEmail(),
                        "2960547803@qq.com",
                        "管理员回复",
                        "尊敬的:" + message.getName() + "先生" + "\n" + "你好,感谢你做出的反馈:" + message.getMessage() + "\n" + message.getFeedbackmessage());
                return "success";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/deleteMessage/{id}")
    public String deleteMessage(@PathVariable("id") Integer id) {
        int i = messageMapper.deleteMessageById(id);
        if (i > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @GetMapping("/findAllUser/{current}/{size}")
    public IPage<User> selectAll3(@PathVariable("current") Integer current,
                                  @PathVariable("size") Integer size) {
        QueryWrapper<User> wrapper = new QueryWrapper();
        Page<User> page = new Page<>(current, size);
        IPage<User> userIPage = userMapper.queryAllUser(page, wrapper);
        List<User> records = userIPage.getRecords();
        for (User record : records) {
            if (record.getSex() == 1) {
                record.setSex1("男");
            } else {
                record.setSex1("女");
            }
        }
        return userIPage;
    }

    @GetMapping("/findAllPetBlog/{current}/{size}")
    public IPage<PetBlog> querryAllpetBlog(@PathVariable("current") Integer current,
                                           @PathVariable("size") Integer size) {
        QueryWrapper<PetBlog> wrapper = new QueryWrapper();
        Page<PetBlog> page = new Page<>(current, size);
        IPage<PetBlog> petblogIPage = petBlogMapper.querryAllpetBlog1(page, wrapper);
        List<PetBlog> records = petblogIPage.getRecords();
        for (PetBlog record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return petblogIPage;
    }

    @GetMapping("/findPetBlogById/{id}")
    public PetBlog findPetBlogById(@PathVariable("id") Integer id) {
        PetBlog petBlog = petBlogMapper.querrypetBlogById(id);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        petBlog.setDatetime(formatter.format(petBlog.getTime()));
        return petBlog;
    }

    @GetMapping("findAllXunWuQiShi/{current}/{size}")
    public IPage<UserUpload> findAllXunWuQiShi(@PathVariable("current") Integer current,
                                               @PathVariable("size") Integer size) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper();
        wrapper.eq("category", "寻物启事");
        Page<UserUpload> page = new Page<>(current, size);
        IPage<UserUpload> userUploadIPage = userUploadMapper.queryUserUpload(page, wrapper);
        List<UserUpload> records = userUploadIPage.getRecords();
        for (UserUpload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return userUploadIPage;
    }

    @GetMapping("findAllSongYang/{current}/{size}")
    public IPage<UserUpload> findAllSongYang(@PathVariable("current") Integer current,
                                             @PathVariable("size") Integer size) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper();
        wrapper.eq("category", "送养");
        Page<UserUpload> page = new Page<>(current, size);
        IPage<UserUpload> userUploadIPage = userUploadMapper.queryUserUpload(page, wrapper);
        List<UserUpload> records = userUploadIPage.getRecords();
        for (UserUpload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return userUploadIPage;
    }

    @GetMapping("findAllZhaoLing/{current}/{size}")
    public IPage<UserUpload> findAllZhaoling(@PathVariable("current") Integer current,
                                             @PathVariable("size") Integer size) {
        QueryWrapper<UserUpload> wrapper = new QueryWrapper();
        wrapper.eq("category", "招领");
        Page<UserUpload> page = new Page<>(current, size);
        IPage<UserUpload> userUploadIPage = userUploadMapper.queryUserUpload(page, wrapper);
        List<UserUpload> records = userUploadIPage.getRecords();
        for (UserUpload record : records) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            record.setDatetime(formatter.format(record.getTime()));
        }
        return userUploadIPage;
    }

    @PostMapping("/sendMessage1")
    public String feedback(@RequestBody JuBaoMessage juBaoMessage) {
        User user = userMapper.queryUserByUserName(juBaoMessage.getJubaousername());
        try {
            mailService.sandSimpleMail("2960547803@qq.com",
                    user.getEmail(),
                    "2960547803@qq.com",
                    "管理员回复",
                    "您于:" + juBaoMessage.getDatetime() + "对用户" + juBaoMessage.getUsername() + "进行了举报，举报理由为："
                            + juBaoMessage.getReason() + "\n" + "经我们进一步核实，发现：" + juBaoMessage.getFeedbackmessage() + "\n" +
                            "感谢您对维护系统环境做出的贡献");
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/admin/insert")
    public String insertadmin(@RequestBody Admin admin) {
        int i = adminMapper.insertAdmin(admin);
        if (i > 0) {
            return "success";
        } else {
            return "false";
        }
    }

    @GetMapping("/user/prohibit/{id}")
    public String updateuserstatus(@PathVariable("id") Integer id) {
        int i = userMapper.updateStatusById(id);
        if (i > 0) {
            return "success";
        } else {
            return "false";
        }
    }
    @GetMapping("/user/jiefeng/{id}")
    public String updateuserstatus1(@PathVariable("id") Integer id) {
        int i = userMapper.updateStatusById1(id);
        if (i > 0) {
            return "success";
        } else {
            return "false";
        }
    }
}


