package com.scott.study.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :TestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/2/21 0021  12:10
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {
    Log log = LogFactory.getLog(TestController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping("/test")
    public String test(){
        log.info("server被调用了！:" +port);
        return "server被调用了！:" +port;
    }

    @RequestMapping(value = "/data")
    public String Data(@RequestParam String name){
        return "传入数据："+name;
    }


}
