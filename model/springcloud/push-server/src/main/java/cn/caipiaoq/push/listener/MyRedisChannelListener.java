package cn.caipiaoq.push.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * redis pub/sub 发布订阅
 */
public class MyRedisChannelListener implements MessageListener {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MyRedisChannelListener.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        try {
            //获取消息内容、消息所在的频道
            String content = new String(body, "UTF-8");
            String p = new String(channel, "UTF-8");
            LOGGER.info("MyRedisChannelListener onMessage channel:{}, content:{}",channel, message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("MyRedisChannelListener onMessage ",e.getMessage());
        }
    }
}
