package com.github.zuihou.commons.constant.msgs;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/12/24
 */
public enum MsgsRecvType {
    /**
     * 按个人
     */
    PERSONAL,
    /**
     * 按角色
     */
    ROLE,
    ;

    public boolean eq(MsgsRecvType type) {
        for (MsgsRecvType t : MsgsRecvType.values()) {
            if (t.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
