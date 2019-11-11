package edu.udacity.java.nano.controller;

import edu.udacity.java.nano.model.Message;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */

    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //Done: add send message method.

        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

     }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //Done: add on open connection.
        onlineSessions.put(session.getId(),session);

        //Done: send message to all who is entering
        sendMessageToAll(JSON.toJSONString(new Message("ENTER", "", "New person entering", onlineSessions.size())));

    }

    private void broadcast(Message message) {
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //Done: add send message.
        //Done: add to message who is sending
        Message message = JSON.parseObject(jsonStr, Message.class);
        sendMessageToAll(JSON.toJSONString(new Message("SPEAK", message.getUsername(), message.getMsg(), onlineSessions.size())));

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //Done: add close connection.
        //Done: send message to all who is leaving
        onlineSessions.remove(session.getId());
        sendMessageToAll(JSON.toJSONString(new Message("LEAVE", "", "person leaving", onlineSessions.size())));

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
