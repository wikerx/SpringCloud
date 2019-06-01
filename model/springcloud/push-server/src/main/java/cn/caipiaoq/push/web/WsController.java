package cn.caipiaoq.push.web;

import cn.caipiaoq.push.config.MyHandler;
import cn.caipiaoq.push.model.WiselyMessage;
import cn.caipiaoq.push.model.WiselyResponse;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

@Controller
public class WsController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(WsController.class);

    @Autowired
    MyHandler myHandler;

	@MessageMapping("/welcome")   //客户端发送消息
	@SendTo("/topic/getResponse") //服务器端向订阅了/topic/getResponse的地址发送消息
	public WiselyResponse say(WiselyMessage message) throws Exception {
        LOGGER.info("/welcome:{}", JSON.toJSONString(message));
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat")
	public void handleChat(String clientId, String msg) {
        LOGGER.info("发送消息:{},接收者：{}", msg, clientId);
		myHandler.sendMessageToUser(clientId,new TextMessage(msg));
	}
}