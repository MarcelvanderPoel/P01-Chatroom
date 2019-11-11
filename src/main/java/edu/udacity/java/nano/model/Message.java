package edu.udacity.java.nano.model;

import org.springframework.messaging.handler.MessagingAdviceBean;

/**
 * WebSocket message model
 */
public class Message {
    // DONE: add message model.

    private String msg;
    private String username;
    private String type;
    private int onlineCount;

    public Message() {}

    public Message(String type, String username, String msg, int onlineCount) {

        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }
}
