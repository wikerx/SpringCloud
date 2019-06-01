package com.github.zuihou.commons.constant.msgs;

import java.util.Arrays;
import java.util.List;

/**
 * 消息中心类型
 * <p>
 * 不满足需求时，需要增加枚举字段
 *
 * @author zuihou
 * @date 2018/12/24
 */
public enum MsgsCenterType {
    WAIT("待办"),
    NOTIFY("通知"),
    PUBLICITY("公示公告"),
    ;
    private String describe;

    MsgsCenterType(String describe) {
        this.describe = describe;
    }

    /**
     * 返回当前系统中可用的类型
     * <p>
     * （迁移到别的系统时，需要禁用某个类型，只需要将这里注释掉即可）
     *
     * @return
     */
    public List<MsgsCenterType> findAvailableList() {
        return Arrays.asList(WAIT, NOTIFY, PUBLICITY);
    }
}
