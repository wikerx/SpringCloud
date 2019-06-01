package com.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 服务不可用
 */
@Component
public class ServerUnAvaiableMessage {
    private static Logger logger = LoggerFactory.getLogger(ServerUnAvaiableMessage.class);
    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.boot.admin.notify.mail.to}")
    private String[] mailTo;

    @RabbitListener(queues = "email-queue")
    public void receiveEmailMessage(String message){
        logger.warn("Received email <"+message+">");
        for(String mail: mailTo){
            sendSimpleMail(message,mail);
        }
    }

    public void sendSimpleMail(String msg,String mailTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(mailTo);
        message.setSubject("主题：服务不可用");
        message.setText(msg);
        mailSender.send(message);
    }
}
