package com.huang.controller;

import com.huang.dao.ContactMapper;
import com.huang.dao.MessageMapper;
import com.huang.dao.UserMapper;
import com.huang.entity.Contact;
import com.huang.entity.Message;
import com.huang.entity.MessageNum;
import com.huang.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@ResponseBody
public class ContactController {
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private UserMapper userMapper;
    private int num=0;

    @PostMapping("/contact")
    public String insertContact(@RequestBody Contact contact){
        contact.setStatus(0);
        int i = contactMapper.insertContact(contact);
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }
    @GetMapping("/findAllContact/{fromname}/{toname}")
    public List<Contact> queryContact(@PathVariable("fromname") String fromname,
                                      @PathVariable("toname") String toname){
        List<Contact> contacts = contactMapper.findAllContact(fromname,toname);
        return contacts;
    }
    @GetMapping("/updateContact/{toname}/{fromname}")
    public void updateContact(@PathVariable("toname") String toname,@PathVariable("fromname") String fromname){
         contactMapper.updateContact(toname, fromname);
    }
    @GetMapping("/findAContactByUsername/{toname}")
    public List<MessageNum> queryContactByToName(@PathVariable("toname") String toname){
        //System.out.println(toname);
        List<String> list=new ArrayList<String>();
        List<MessageNum> list1=new ArrayList<MessageNum>();
        List<Contact> contacts = contactMapper.findAllContactByToName(toname);
                for(Contact contact : contacts){
                    list.add(contact.getFromname());
                }
        //System.out.println(contacts);
        Set<String> set = new HashSet<>(list);
                for (String set1 : set){
                    List<Contact> i = contactMapper.findAllContactByToName2(toname, set1);
                    //System.out.println(i);
                    if(i.size()!=0){
                        User user = userMapper.queryUserByUserName(set1);
                        MessageNum messageNum =new MessageNum();
                        messageNum.setUsername(set1);
                        messageNum.setPortrait(user.getPortrait());
                        messageNum.setNum(i.size());
                        list1.add(messageNum);
                    }else {
                        User user = userMapper.queryUserByUserName(set1);
                        //System.out.println(user);
                        MessageNum messageNum =new MessageNum();
                        messageNum.setUsername(set1);
                        messageNum.setPortrait(user.getPortrait());
                        messageNum.setNum(0);
                        list1.add(messageNum);
                    }
                }
        return list1;
    }
    @GetMapping("/findAContactByUsername1/{toname}")
    public Integer queryContactByToName1(@PathVariable("toname") String toname){
        List<Contact> contacts = contactMapper.findAllContactByToName1(toname);
        return contacts.size();
    }
//    @GetMapping("/findAContactByUsername2/{toname}")
//    public List<MessageNum> queryContactByToName2(@PathVariable("toname") String toname) {
//        List<String> list = new ArrayList<String>();
//        List<MessageNum> list1 = new ArrayList<MessageNum>();
//        List<Contact> contacts = contactMapper.findAllContactByToName1(toname);
//        if (contacts.size() != 0) {
//            for (Contact contact : contacts) {
//                list.add(contact.getFromname());
//            }
//            Set<String> set = new HashSet<>(list);
//            for (String set1 : set) {
//                User user = userMapper.queryUserByUserName(set1);
//                MessageNum messageNum = new MessageNum();
//                messageNum.setUsername(user.getUsername());
//                messageNum.setPortrait(user.getPortrait());
//                List<Contact> contacts2 = contactMapper.findAllContactByToName2(toname, user.getUsername());
//                messageNum.setNum(contacts2.size());
//                list1.add(messageNum);
//            }
//            return list1;
//        }else {
//            return queryContactByToName(toname);
//        }
//    }

}
