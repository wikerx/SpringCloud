//package com.github.zuihou.msgs.queue;
//
//import com.alibaba.fastjson.JSONObject;
//import com.github.zuihou.msgscenter.dto.MsgsCenterInfoSaveDTO;
//import com.github.zuihou.msgscenter.dto.MsgsCenterInfoUpdateDTO;
//import com.github.zuihou.msgscenter.service.MsgsCenterInfoService;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * This is a Description
// *
// * @author zuihou
// * @date 2018/12/26
// */
//@Component
//public class MsgsCenterQueueConsumer extends BaseQueue {
//
//    @Autowired
//    private MsgsCenterInfoService msgsCenterInfoService;
//
//    /**
//     * 创建bean 的作用和 init 方法中的一样。
//     * 没有这个，启动会报错
//     *
//     * @return
//     */
//    @Bean
//    public Queue sendQueue() {
//        return new Queue(getRoutingKey(MSGSCENTER_SAVE));
//    }
//
//    @Bean
//    public Queue updateQueue() {
//        return new Queue(getRoutingKey(MSGSCENTER_UPDATE));
//    }
//
//    @RabbitListener(queues = "${spring.rabbitmq.key-prefix}" + MSGSCENTER_SAVE)
//    @RabbitHandler
//    public void saveMsgsCenter(String param) {
//        execute(param, (curParam) -> {
//            MsgsCenterInfoSaveDTO save = JSONObject.parseObject(curParam, MsgsCenterInfoSaveDTO.class);
//            msgsCenterInfoService.saveMsgs(save);
//        });
//    }
//
//    @RabbitListener(queues = "${spring.rabbitmq.key-prefix}" + MSGSCENTER_UPDATE)
//    @RabbitHandler
//    public void updateMsgsCenterStatus(String param) {
//        execute(param, (curParam) -> {
//            MsgsCenterInfoUpdateDTO update = JSONObject.parseObject(curParam, MsgsCenterInfoUpdateDTO.class);
//            msgsCenterInfoService.updateMsgsReadStatus(update);
//        });
//    }
//
//
//}
