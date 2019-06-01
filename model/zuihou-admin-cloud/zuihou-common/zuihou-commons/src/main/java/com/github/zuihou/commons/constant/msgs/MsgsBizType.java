package com.github.zuihou.commons.constant.msgs;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/12/24
 */
public enum MsgsBizType {

    MAIL_SEND("新邮件"),
    WARN_SEND("发送预警"),
    STANDARD_APPLY("标准分类申请"),
    STANDARD_AUDIT("标准分类审批"),
    MT_APPR_AUDIT("考勤审批"),
    MT_LOG_VIEW("日志查看"),
    MT_LOGS_COMMENT("日志评论"),

    DEVELOPER_APPLICATION_AUDIT("新增应用申请"),
    DEVELOPER_APPLICATION_AUDIT_RESULT("新增应用审批结果"),
    DEVELOPER_MODULE_AUDIT("新增功能申请"),
    DEVELOPER_MODULE_AUDIT_RESULT("功能新增审批结果"),
    DEVELOPER_CALL_AUDIT("新增调用申请"),
    DEVELOPER_CALL_AUDIT_RESULT("功能调用审批结果"),
    DEVELOPER_MODULE_UPDATE("功能状态变更"),
    ;

    /**
     * 对应#MsgsCenterInfo 表的 #bizTypeDescribe 字段执行
     * 通过 %s 进行占位，然后调用format方法传递 变量进行替换
     * <p>
     * 请勿自行使用 字符串进行 拼接！！！
     */
    private String describe;
    private Object[] args;

    MsgsBizType(String describe) {
        this.describe = describe;
    }

    /**
     * 对应#MsgsCenterInfo 表的 #bizType 字段
     * 命名规则： 系统_模块_业务 (大写,长度不超过64个字符)
     * eg:  DEVELOPER_APPLICATION_AUDIT  （服务监控系统中应用模块的审核功能）
     */
    public String getCode() {
        return this.name().toUpperCase();
    }

    public String getDescribe() {
        return format(this.args);
    }

    public Object[] getArgs() {
        return args;
    }

    public MsgsBizType setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    public String format(Object... args) {
        return String.format(this.describe, args);
    }


}
