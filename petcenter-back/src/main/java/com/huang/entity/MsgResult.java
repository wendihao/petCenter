package com.huang.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 侠客
 * Date 2022/8/15 20:56
 * Description： 服务器发给浏览器的webSocket数据
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class MsgResult {
    private boolean systemMsgFlag;
    private String from;
    private Object sendMsg;
}
