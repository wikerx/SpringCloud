package com.scott.study.controller;

import com.scott.study.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :TestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  10:56
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {
    Log log = LogFactory.getLog(TestController.class);

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String Test(@RequestParam("name")String name){
        return testService.test(name);
    }

    @GetMapping("/test1")
    public String Test1(){
        return testService.test1();
    }

    @GetMapping("/teststudy01")
    public String testStudy01(){
        log.info("调用study01.......111......");
        return testService.testStudy01();
    }


}
