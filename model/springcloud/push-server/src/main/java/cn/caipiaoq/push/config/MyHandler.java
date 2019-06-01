package cn.caipiaoq.push.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class MyHandler implements WebSocketHandler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MyHandler.class);

    //在线用户列表
    private static final Map<String, WebSocketSession> users;

    static {
        users = new HashMap<>();
    }    //新增socket

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ID = session.getUri().toString().split("ID=")[1];
        LOGGER.info("成功建立连接,ID:{}",ID);
        if (ID != null) {
            users.put(ID, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            LOGGER.info("id:{},session:{}",ID,session);
        }
        LOGGER.info("当前在线人数：{}",users.size());
    }

    //接收socket信息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        try {
            LOGGER.info("handleMessage:{}", webSocketMessage.getPayload());
            JSONObject jsonObject = JSON.parseObject(String.valueOf(webSocketMessage.getPayload()));
            String sendUser = String.valueOf(webSocketSession.getAttributes().get("WEBSOCKET_USERID"));
            String message = String.valueOf(jsonObject.get("message"));
            String reciverUser = String.valueOf(jsonObject.get("id"));
            LOGGER.info("发送者：{},发送消息:{},接收者：{}", sendUser, message, reciverUser);
            sendMessageToUser(reciverUser, new TextMessage(message));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("handleMessage出现异常:{}",e.getMessage());
        }
    }

    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null)
            return false;

        WebSocketSession session = users.get(clientId);
        if (!session.isOpen())
            return false;

        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("sendMessageToUser出现异常:{}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("sendMessageToAllUsers出现异常:{}",e.getMessage());
                allSendSuccess = false;
            }
        }
        return allSendSuccess;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        LOGGER.warn("连接出错");
        users.remove(getClientId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.warn("连接已关闭：{}" ,status);
        users.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get("WEBSOCKET_USERID");
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }

}
