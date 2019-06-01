package com.github.zuihou.commons.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.AntPathMatcher;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/01/03
 */
public class IgnoreTokenConfig {
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    public static final List<String> LIST = Arrays.asList(
            "/error",
            "/actuator/**",
            "/gate/**",
            "/static/**",
            "/**/swagger-ui.html",
            "/**/webjars/**",
            "/**/v2/api-docs/**",
            "/**/swagger-resources/**",
            "/anon/**",
            "/*/anon/**"
//            "/admin/**/token",
//            "/admin/**/verify",
//            "/admin/**/invalid",
//            "/**/token",
//            "/**/verify",
//            "/**/invalid",
//            "/**/usertoken"

    );
    public static final List<String> USER_TOKEN_LIST = Arrays.asList();

    public static boolean isIgnoreToken(String currentUri) {
        return isIgnore(LIST, currentUri);
    }

    public static boolean isIgnoreUserToken(String currentUri) {
        List<String> list = new ArrayList<>();
        list.addAll(LIST);
        list.addAll(USER_TOKEN_LIST);
        return isIgnore(list, currentUri);
    }

    public static boolean isIgnore(List<String> list, String currentUri) {
        if (list.isEmpty()) {
            return false;
        }
        return list.stream().anyMatch((url) ->
                currentUri.startsWith(url) || ANT_PATH_MATCHER.match(url, currentUri)
        );
    }
}
