package com.scott.study.action;

import com.scott.study.utils.HttpClientUtils;

/**
 * @CLASSNAME :TestRibbon
 * @Description :负载测试
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  11:09
 * @Version :V1.0
 * @Status : 编写
 **/
public class TestRibbon {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            String result = HttpClientUtils.doGet("http://localhost:8764/test1");
            String result = HttpClientUtils.doGet("http://localhost:8765/teststudy01");
            System.out.println("result:" + result);
        }
    }
}
