package com.github.zuihou.commons.exception.core;


import com.github.zuihou.exception.code.BaseExceptionCode;

/**
 * 业务系统错误码
 * @author zuihou
 * @createTime 2017-12-13 16:22
 */
public enum ExceptionCode implements BaseExceptionCode {

    //系统相关 start
    SUCCESS(0, "成功"),
    SYSTEM_BUSY(-1, "系统繁忙"),
    SYSTEM_TIMEOUT(-2, "服务超时"),
    PARAM_EX(-3, "参数类型解析异常"),
    SQL_EX(-4, "数据库异常"),
    NULL_POINT_EX(-5, "空指针异常"),
    ILLEGALA_RGUMENT_EX(-6, "无效参数异常"),
    MEDIA_TYPE_EX(-7, "请求类型异常"),
    LOAD_RESOURCES_ERROR(-8, "加载资源出错"),
    BASE_VALID_PARAM(-9, "统一验证参数异常"),  //适用于controller层的统一参数验证
    OPERATION_EX(-10, "操作异常"),    // 适用于 所有方法执行可预知的失败返回

    OK(200, "OK"),
    BAD_REQUEST(400, "错误的请求"),
    /**
     * {@code 401 Unauthorized}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    UNAUTHORIZED(401, "未经授权"),
    /**
     * {@code 404 Not Found}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    NOT_FOUND(404, "没有找到资源"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),

    TOO_MANY_REQUESTS(429, "请求超过次数限制"),
    INTERNAL_SERVER_ERROR(500, "内部服务错误"),
    BAD_GATEWAY(502, "网关错误"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    //系统相关 end

    //DB相关 10000 start
    DB_REMOVE_ERROR(10000, "无法软删除"),
    PAGE_OPENAPIREQ(10001, "分页参数,不能为空"),
    //DB相关 end

    REQUIRED_FILE_PARAM_EX(63001, "请求中必须至少包含一个有效文件"),

    //jwt token 相关 start
    JWT_TOKEN_EXPIRED(40001, "token超时，请检查 token 的有效期"),//过期
    JWT_SIGNATURE(40002, "不合法的token，请认真比对 token 的签名"),//签名错误
    JWT_ILLEGAL_ARGUMENT(40003, "缺少token参数"),//token 为空
    JWT_GEN_TOKEN_FAIL(40004, "生成token失败"),
    JWT_PARSER_TOKEN_FAIL(40005, "解析token失败"),
    JWT_APPID_SECRET_INVALID(40006, "生成token时 AppSecret 错误，或者 AppId 无效！"),
    JWT_APPID_ENABLED(40007, "AppId 已经被禁用！请联系管理员"),
    //jwt token 相关 end


    JWT_USER_TOKEN_EXPIRED(40011, "用户token超时，请检查 token 的有效期"),
    JWT_USER_SIGNATURE(40012, "不合法的用户token，请认真比对 token 的签名"),//签名错误
    JWT_USER_ILLEGAL_ARGUMENT(40013, "缺少用户token参数"),//token 为空
    JWT_USER_GEN_TOKEN_FAIL(40014, "生成用户token失败"),
    JWT_USER_PARSER_TOKEN_FAIL(40015, "解析用户token失败"),
    JWT_USER_ENABLED(40016, "生成user-token时，发现用户已经被禁用！"),
    JWT_USER_INVALID(40017, "生成user-token时，检测用户信息无效"),


    //权限相关 start
    CLIENT_FORBIDDEN(50001, "客户端被禁止!"),
    USER_NAME_PWD_ERROR(50002, "帐号或者密码错误"),
    USER_NAME_EXIST(50003, "帐号已存在"),
    USER_NOT_EXIST(50004, "帐号不存在"),
    //权限相关 end

    //资源管理相关 start
    MENU_GROUP_NULL(51000, "菜单组不能为空"),
    MENU_GROUP_CODE_EMPTY(51001, "菜单组编码不能为空"),
    MENU_GROUP_EXIST(51002, "菜单组CODE已存在"),
    MENU_GROUP_TOO_MUCH(51003, "菜单组最多只能创建20个"),
    MENU_GROUP_ID_NULL(51004, "菜单组id不能为空"),
    MENU_GROUP_EXIST_CHILD(51005, "该菜单组存在子菜，无法删除"),

    MENU_NULL(51100, "菜单不能为空"),
    MENU_CODE_EMPTY(51101, "菜单编码[code]不能为空"),
    MENU_TYPE_EMPTY(51102, "菜单类型[type]不能为空"),
    MENU_GROUP_NOT_EXIST(51103, "菜单组编码[code]不存在"),
    MENU_TOO_MUCH(51104, "每组菜单最多只能创建500个"),
    MENU_ID_NULL(51105, "菜单[id]不能为空"),
    MENU_EXIST_CHILD(51106, "该菜单存在子菜单或子资源，无法删除"),

    RESOURCES_NULL(51200, "资源信息不能为空"),
    RESOURCES_CODE_EMPTY(51201, "资源编码[code]不能为空"),
    RESOURCES_MENU_ID_NULL(51202, "资源菜单id[menuId]不能为空"),
    RESOURCES_TYPE_NULL(51203, "资源类型[type]不能为空"),
    RESOURCES_EXIST(51204, "菜单/资源编码[code]已存在"),
    RESOURCES_ID_NULL(51205, "资源id[id]不能为空"),
    MENU_NOT_EXIST(51206, "资源所属菜单不存在"),
    //资源管理相关 end

    //角色管理相关 start
    ROLE_NULL(52000, "角色信息不能为空"),
    ROLE_CODE_EMPTY(52001, "角色编码[code]不能为空"),
    ROLE_CODE_EXIST(52002, "角色编码[code]已存在"),
    ROLE_ID_NULL(52003, "角色[id]不能为空"),
    //角色管理相关 end

    //帐号管理相关 start
    USER_EXIST(53000, "登录名username已存在"),
    USER_ID_NULL(53001, "用户id不能为空"),
    USER_NAME_EMPTY(53002, "登录名username不能为空"),
    USER_PWD_EMPTY(53003, "密码不能为空"),
    USER_PWD_NOT_EQUALS(53004, "密码与确认密码不一致"),
    USER_NULL(53005, "用户信息不能为空"),

    DEPART_EXIST(53501, "部门code已存在"),
    DEPART_NULL(53502, "部门信息不能为空"),
    DEPART_ID_NULL(53503, "部门id不能为空"),
    DEPART_CODE_EMPTY(53504, "部门code不能为空"),
    DEPART_CODE_EXIST(53505, "部门code已经存在"),
    DEPART_PARENT_NOT_EXIST(53506, "父部门不存在"),
    //帐号管理相关 end

    //数据字典相关 start
    DICTIONARY_TYPE_NULL(54000, "数据字典类型不能为空"),
    DICTIONARY_TYPE_ID_NULL(54001, "数据字典类型id不能为空"),
    DICTIONARY_TYPE_CODE_EMPTY(54002, "数据字典类型code不能为空"),
    DICTIONARY_TYPE_CODE_EXIST(54003, "数据字典类型code不能重复"),
    DICTIONARY_TYPE_EXIST(54004, "数据字典类型不存在"),

    DICTIONARY_NULL(54500, "数据字典不能为空"),
    DICTIONARY_ID_NULL(54501, "数据字典id不能为空"),
    DICTIONARY_CODE_EMPTY(54502, "数据字典code不能为空"),
    DICTIONARY_CODE_EXIST(54503, "数据字典code不能重复"),
    //数据字典相关 end


    // 文件夹相关 start
    FOLDER_NULL(55000, "文件夹为空"),
    FOLDER_NAME_EMPTY(55001, "文件夹名称为空"),
    FOLDER_PARENT_NULL(55002, "父文件夹为空"),


    FILE_NULL(55500, "文件为空"),
    FILE_NAME_EMPTY(55501, "文件名称为空"),
    FILE_FOLDER_NULL(55502, "文件夹为空"),

    // 文件夹相关 end
    ;
    private  int code;
    private  String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {

        return msg;
    }

    public ExceptionCode build(String msg, Object... param) {
        this.msg = String.format(msg, param);
        return this;
    }

    public ExceptionCode param(Object... param) {
        this.msg = String.format(msg, param);
        return this;
    }
}
