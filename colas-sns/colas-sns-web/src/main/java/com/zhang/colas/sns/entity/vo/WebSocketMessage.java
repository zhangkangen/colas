package com.zhang.colas.sns.entity.vo;

import java.io.Serializable;

/**
 * 自定义封装包含发送信息的实体类
 * @author zxk
 * @date 2018-1-22 10:00:44
 */
public class WebSocketMessage implements Serializable {

    private static final long serialVersionUID = -1596616915101577749L;

    /**
     * 发送广播消息，发送地址：/topic/* ，*为任意名字，如取名monitor,则客户端对应订阅地址为：/topic/monitor
     * 发送私人消息，发送地址：/*，*为任意名字，这里取名为message,客户端对应订阅地址：/user/{自定义客户端标识ID}/message
     */

    /**
     * 可以自定义其他属性
     */
    private String distination;
    /**
     * 实际发送的数据对象
     */
    private Object data;

    private String userId;


    public String getDistination() {
        return distination;
    }

    public void setDistination(String distination) {
        this.distination = distination;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
