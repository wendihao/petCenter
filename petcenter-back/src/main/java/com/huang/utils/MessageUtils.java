package com.huang.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.entity.MsgResult;


public class MessageUtils {

    /**
     * 封装响应的消息，系统消息的发送fromName为null;
     * 封装好的响应如下，例如
     *   系统消息： {“systemMsgFlag”: true, "fromName": null, "message": ["Name1", "Name2"]}.
     *   非系统消息 {“systemMsgFlag”: false, "fromName": "YYJ", "message": “你在哪里呀？”}.
     * @param systemMsgFlag 是否是系统消息
     * @param from 发送方名称
     * @param message 发送的消息内容
     * @return java.lang.String json字符串
     */
    public static String getMessage(boolean systemMsgFlag, String from, Object message) {
        MsgResult resultMessage = new MsgResult();
        resultMessage.setSystemMsgFlag(systemMsgFlag);
        resultMessage.setSendMsg(message);
        System.out.println();
        if (!systemMsgFlag) {
            resultMessage.setFrom(from);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String repStr = null;
        try {
            repStr = objectMapper.writeValueAsString(resultMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return repStr;
    }
}