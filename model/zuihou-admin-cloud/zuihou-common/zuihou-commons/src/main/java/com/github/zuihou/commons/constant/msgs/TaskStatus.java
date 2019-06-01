package com.github.zuihou.commons.constant.msgs;

public enum TaskStatus {
    /**
     * 待发送
     */
    WAITING(0, "等待执行", "等待发送"),
    /**
     * 发送成功
     */
    SUCCESS(1, "执行成功", "发送成功"),
    /**
     * 发送失败
     */
    FAIL(2, "执行失败", "发送失败"),
    ;
    private int status;
    private String describe;
    private String sendDescribe;

    TaskStatus(int status, String describe, String sendDescribe) {
        this.status = status;
        this.describe = describe;
        this.sendDescribe = sendDescribe;
    }

    public int getStatus() {
        return status;
    }

    public String getDescribe() {
        return describe;
    }

    public String getSendDescribe() {
        return sendDescribe;
    }

    public boolean eq(Integer code) {
        if (code == null) {
            return false;
        }
        for (TaskStatus status : TaskStatus.values()) {
            if (status.getStatus() == code) {
                return true;
            }
        }
        return false;
    }
}
