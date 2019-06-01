package com.github.zuihou.msgs.dto;


import com.github.zuihou.msgs.entity.MsgsMobile;

import lombok.Data;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2018/12/26
 */

@Data
public class MsgsMobileDTO extends MsgsMobile {
    private Integer totalNum = 0;
    private Integer notReadNum= 0;
    private Integer readNum= 0;
}
