package com.github.zuihou.msgscenter.queue;

import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.commons.queue.BaseQueue;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoSaveDTO;
import com.github.zuihou.msgscenter.dto.MsgsCenterInfoUpdateDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/25
 */
@Slf4j
@Component
public class MsgsCenterQueueProducer extends BaseQueue {

    /**
     * 保存消息
     *
     * @param dto
     */
    public void saveMsgsCenter(MsgsCenterInfoSaveDTO dto) {
        execute(MSGSCENTER_SAVE, JSONObject.toJSONString(dto));
    }

    /**
     * 更新 消息状态
     *
     * @param dto
     */
    public void updateMsgsCenterStatus(MsgsCenterInfoUpdateDTO dto) {
        execute(MSGSCENTER_UPDATE, JSONObject.toJSONString(dto));
    }


}
