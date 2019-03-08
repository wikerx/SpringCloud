package com.scott.study.controller;

import com.scott.study.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :TestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  15:38
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {

    @Autowired
    HystrixService hystrixService;

    @GetMapping("/test1")
    public String Test1(){
        return hystrixService.success();
    }

/*
    熔断器的使用：
    启动 studyNo2的Client 访问:http://localhost:8767/test1 显示调用的的信息
    关闭 studyNo2的Client 再次访问:http://localhost:8767/test1 显示isError中的信息，表示，短路默认走这个方法了
*/




}
