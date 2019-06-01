package com.github.zuihou.commons.constant;

/**
 * 布尔类型状态码
 *
 * @author zuihou
 */
public enum BooleanStatus {
    TRUE(true, 1, "是"),
    FALSE(false, 0, "否"),
    ;
    /**
     * 值
     */
    private Boolean val;
    private Integer status;
    /**
     * 描述
     */
    private String describe;

    BooleanStatus(Boolean val, Integer status, String describe) {
        this.val = val;
        this.status = status;
        this.describe = describe;
    }

    public Boolean getVal() {
        return val;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusStr() {
        return String.valueOf(status);
    }

    public static BooleanStatus parse(Boolean val) {
        if (val == null) {
            return FALSE;
        }
        for (BooleanStatus status : BooleanStatus.values()) {
            if (status.getVal().equals(val)) {
                return status;
            }
        }
        return FALSE;
    }
}
