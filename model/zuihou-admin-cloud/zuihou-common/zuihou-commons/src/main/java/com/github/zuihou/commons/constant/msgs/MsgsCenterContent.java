package com.github.zuihou.commons.constant.msgs;

/**
 * 消息中心的uri、标题和内容的字符串配置
 *
 * @author zuihou
 * @date 2018/12/26
 */
public enum MsgsCenterContent {
    MAIL_SEND("/index.html#/received", "您有一封新邮件《%s》。", "您有一封新邮件《%s》。"),
    WARN_SEND("/#/ledgerList", "预警信息：%s", "您有一条新的预警信息：%s。"),
    STANDARD_APPLY("/module/index?promUrl=%s/standard/toDoList", "%s标准分类申请", "您有一个%s标准分类申请待审批."),
    STANDARD_APPLY_PASS("/module/index?promUrl=%s/standard/applyList", "%s标准分类审批通过", "您申请的%s标准分类已审批通过."),
    STANDARD_APPLY_REJECT("/module/index?promUrl=%s/standard/applyList", "%s标准分类被驳回", "您申请的%s标准分类被驳回."),
    MT_APPLY_AUDIT("/module/index?promUrl=/%s/fast/workapprove/list", "您有一条%s的%s%s申请待审批", "您有一条%s的%s%s申请待审批"),
    MT_APPLY_RESULT("/module/index?promUrl=/%s/fast/workapprove/list", "%s%s", "您申请的%s%s"),
    MT_LOGS_VIEW("/module/index?promUrl=/%s/module/leaderview", "您有一条%s的日志待查看", "您有一条%s的日志待查看"),
    MT_LOGS_COMMENT("", "%s评论了您的日志", "日志评论"),

    DEVELOPER_APPLICATION_AUDIT("%s/module/myTaskList?appName=%s","申请%s接入平台","%s申请%s应用接入到平台\\n。"),
    DEVELOPER_APPLICATION_RESULT("%s/module/myTaskList?appName=%s","%s审批%s","您申请接入到平台的%s应用审批%s"),

    DEVELOPER_MODULE_AUDIT("%s/module/myTaskList?moduleName=%s","申请调用%s","%s申请调用%s功能待审批。"),
    DEVELOPER_MODULE_RESULT("%s/module/myTaskList?moduleName=%s","%功能审批%s","您申请新增的%s功能审批%s."),
    DEVELOPER_MODULE_ENABLE("%s/module/helpDoc?moduleId=%s","功能%s","您的%s应用申请调用的%s功能，已%s"),

    DEVELOPER_CALL_AUDIT("%s/module/myTaskList?appName=%s","申请调用%s","%s申请调用%s功能待审批"),
    DEVELOPER_CALL_RESULT("%s/module/myTaskList?appName=%s","%功能调用审批%s","您申请调用的%s功能审批%s."),

    ;

    private String url;
    private String title;
    private String content;
    private Object[] args;

    MsgsCenterContent(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public MsgsCenterContent setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    public String getUrl() {
        return format(url, args);
    }

    public String getTitle() {
        return format(title, args);
    }

    public String getContent() {
        return format(content, args);
    }


    public String getUrl(Object[] args) {
        return format(url, args);
    }

    public String getTitle(Object[] args) {
        return format(title, args);
    }

    public String getContent(Object[] args) {
        return format(content, args);
    }

    public String format(String val, Object... args) {
        return String.format(val, args);
    }


}
