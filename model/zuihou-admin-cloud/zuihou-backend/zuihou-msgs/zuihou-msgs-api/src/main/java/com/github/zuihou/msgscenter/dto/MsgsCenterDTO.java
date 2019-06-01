package com.github.zuihou.msgscenter.dto;

import com.github.zuihou.msgscenter.entity.MsgsCenterInfo;

import lombok.Data;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/26
 */
@Data
public class MsgsCenterDTO extends MsgsCenterInfo {

    private Integer totalNum = 0;
    private Integer notReadNum = 0;
}
