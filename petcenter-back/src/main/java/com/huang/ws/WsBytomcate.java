package com.huang.ws;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.controller.MyTest;
import com.huang.dao.UserMapper;
import com.huang.entity.ReSultMsg;
import com.huang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
 import javax.websocket.server.PathParam;
 import javax.websocket.server.ServerEndpoint;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
 @ServerEndpoint(value="/Room/{username}",configurator = GetHttpSessionConfigurator.class) // 当创建好一个（服务）端点之后，将它以一个指定的URI发布到应用当中，这样远程客户端就能连接上它了
 public class WsBytomcate {
    private Session session;
    //保存当前登录浏览器的用户
    private HttpSession httpSession;
    @Autowired
    private UserMapper userMapper;
    private static Map<String, WsBytomcate> onLineUsers = new ConcurrentHashMap<>();
    //private static List<Session> sessions = new ArrayList<Session>();

    @OnOpen
    public void OnOpen(Session session,EndpointConfig config, @PathParam(value = "username") String username) throws JsonProcessingException {
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        onLineUsers.put(username,this);
        //sessions.add(session);
        ReSultMsg reSultMsg = new ReSultMsg();
        reSultMsg.setUsername(username);
        String msg = "加入聊天";
        reSultMsg.setMsg(msg);
        reSultMsg.setPortrait("0");
        ObjectMapper mapper = new ObjectMapper();
        String resultmsg = mapper.writeValueAsString(reSultMsg);
        //System.out.println(resultmsg+"1");
        sendTextMsg(resultmsg);
    }

    @OnMessage
    public void OnMsg(String msg, Session session, @PathParam(value = "username") String username) throws IOException {
        //System.out.println(2222);
        ReSultMsg reSultMsg = new ReSultMsg();
        User user = MyTest.test(username);
        reSultMsg.setUsername(username);
        reSultMsg.setMsg(msg);
        reSultMsg.setPortrait(user.getPortrait());
        //sendTextMsg(reSultMeg);
        ObjectMapper mapper = new ObjectMapper();
        String resultmsg = mapper.writeValueAsString(reSultMsg);
        Set<String> names = onLineUsers.keySet();
        //System.out.println(names);
        for (String name : names) {
            if (!name.equals(username)) {
                WsBytomcate wsBytomcate = onLineUsers.get(name);
                //System.out.println(resultmsg+"2");
                wsBytomcate.session.getBasicRemote().sendText(resultmsg);
            }
        }
    }

    @OnClose
    public void OnClose(Session session, @PathParam("username") String username) throws IOException {
        onLineUsers.remove(username);
        //sessions.remove(session);
        ReSultMsg reSultMsg = new ReSultMsg();
        reSultMsg.setUsername(username);
        String msg = "退出聊天";
        reSultMsg.setMsg(msg);
        reSultMsg.setPortrait("0");
        ObjectMapper mapper = new ObjectMapper();
        String resultmsg = mapper.writeValueAsString(reSultMsg);
        //System.out.println(resultmsg+"3");
        sendTextMsg(resultmsg);
    }

    @OnError
    public void OnError(Throwable e) {
        e.printStackTrace();
    }

    private Set<String> getNames() {
        return onLineUsers.keySet();
    }

    private void sendTextMsg(String msg) {
        try {
            Set<String> names = onLineUsers.keySet();
            //System.out.println(names);
            for (String name : names) {
                WsBytomcate wsBytomcate = onLineUsers.get(name);
               // System.out.println(wsBytomcate);
                wsBytomcate.session.getAsyncRemote().sendText(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}