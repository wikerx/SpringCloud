package com.github.zuihou.context;

import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.utils.basetype.NumberHelper;
import com.github.zuihou.utils.basetype.StringHelper;


/**
 * 获取当前域中的 用户id appid 用户昵称
 * 注意： appid 通过token解析，  用户id 和 用户昵称必须在前端 通过请求头的方法传入。 否则这里无法获取
 *
 * @author zuihou
 * @createTime 2017-12-13 16:52
 */
public class BaseContextHandler {
    public static final ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, String.valueOf(value));
    }

    private static Map<String, String> getLocalMap() {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map;
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    public static Long getUserId() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_USER_ID);
        return NumberHelper.longValueOfNil(value);
    }

    public static void setUserId(Long userId) {
        set(BaseContextConstants.JWT_USER_KEY_USER_ID, userId);
    }

    /**
     * 用户昵称
     */
    public static String getName() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_NAME);
        return returnObjectValue(value);
    }

    public static void setName(String name) {
        set(BaseContextConstants.JWT_USER_KEY_NAME, name);
    }

    public static String getAccount() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_ACCOUNT);
        return returnObjectValue(value);
    }

    public static void setAccount(String account) {
        set(BaseContextConstants.JWT_USER_KEY_ACCOUNT, account);
    }

    public static Integer getLoginFlag() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_LOGIN_FLAG);
        return NumberHelper.intValueOfNil(value);
    }

    public static void setLoginFlag(Integer loginFlag) {
        set(BaseContextConstants.JWT_USER_KEY_LOGIN_FLAG, loginFlag);
    }

    public static Long getRoles() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_ROLES);
        return NumberHelper.longValueOfNil(value);
    }

    public static void setRoles(Long roles) {
        set(BaseContextConstants.JWT_USER_KEY_ROLES, roles);
    }

    /**
     * appName
     *
     * @return
     */
    public static String getAppName() {
        Object value = get(BaseContextConstants.JWT_APP_KEY_APP_NAME);
        return StringHelper.getObjectValue(value);
    }

    /**
     * 设置用户id
     *
     * @param adminId
     */
    public static void setAdminId(Long adminId) {
        setUserId(adminId);
    }

    /**
     * 设置昵称
     *
     * @param name
     */
    public static void setAppName(String name) {
        set(BaseContextConstants.JWT_APP_KEY_APP_NAME, name);
    }

    public static void setUserId(String userId) {
        setUserId(NumberHelper.longValueOf0(userId));
    }

    /**
     * 获取应用id
     *
     * @return
     */
    public static String getAppId() {
        Object value = get(BaseContextConstants.JWT_APP_KEY_APP_ID);
        return StringHelper.getObjectValue(value);
    }

    /**
     * 设置应用id
     *
     * @param appId
     */
    public static void setAppId(String appId) {
        set(BaseContextConstants.JWT_APP_KEY_APP_ID, appId);
    }

    public static String getToken() {
        Object value = get(BaseContextConstants.TOKEN_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getUserToken() {
        Object value = get(BaseContextConstants.USER_TOKEN_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static void setRoles(String roles) {
        setRoles(NumberHelper.longValueOf0(roles));
    }

    public static void setToken(String token) {
        set(BaseContextConstants.TOKEN_NAME, token);
    }

    public static void setUserToken(String token) {
        set(BaseContextConstants.USER_TOKEN_NAME, token);
    }

    public static void setLoginFlag(String loginFlag) {
        setLoginFlag(NumberHelper.intValueOf0(loginFlag));
    }

    public static String getOrgId() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_ORGID);
        return StringHelper.getObjectValue(value);
    }

    public static void setOrgId(String val) {
        set(BaseContextConstants.JWT_USER_KEY_ORGID, val);
    }

    public static String getOrgName() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_ORGNAME);
        return StringHelper.getObjectValue(value);
    }

    public static void setOrgName(String val) {
        set(BaseContextConstants.JWT_USER_KEY_ORGNAME, val);
    }

    public static String getDepartmentId() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_DEPARTMENTID);
        return StringHelper.getObjectValue(value);
    }

    public static String getOrgCode() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_ORGCODE);
        return StringHelper.getObjectValue(value);
    }

    public static void setOrgCode(String val) {
        set(BaseContextConstants.JWT_USER_KEY_ORGCODE, val);
    }

    public static void setDepartmentId(String val) {
        set(BaseContextConstants.JWT_USER_KEY_DEPARTMENTID, val);
    }

    public static String getDepartmentName() {
        Object value = get(BaseContextConstants.JWT_USER_KEY_DEPARTMENTNAME);
        return StringHelper.getObjectValue(value);
    }

    public static void setDepartmentName(String val) {
        set(BaseContextConstants.JWT_USER_KEY_DEPARTMENTNAME, val);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
