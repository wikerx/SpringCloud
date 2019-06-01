package com.github.zuihou.commons.constant;

/**
 * 草稿
 *
 * @author zuihou
 * @date 2018/12/20
 */
public enum DraftStatus {
    DRAFT(true, 1, "草稿"),
    NOT_DRAFT(false, 0, "非草稿"),;

    private Boolean val;
    private int status;
    private String describe;

    DraftStatus(Boolean val, int status, String describe) {
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

    public int getStatus() {
        return status;
    }

    public static DraftStatus match(Boolean val) {
        if (val == null) {
            return NOT_DRAFT;
        }
        for (DraftStatus status : DraftStatus.values()) {
            if (status.getVal().equals(val)) {
                return status;
            }
        }
        return NOT_DRAFT;
    }
}
