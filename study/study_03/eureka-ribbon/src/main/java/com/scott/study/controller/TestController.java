package com.scott.study.controller;

import com.scott.study.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :TestController
 * @Description :测试
 * @Author :Mr.薛
 * @Data :2019/2/22 0022  10:48
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public String test() {
        System.out.println("----------test");
        return testService.testService();
    }
}
