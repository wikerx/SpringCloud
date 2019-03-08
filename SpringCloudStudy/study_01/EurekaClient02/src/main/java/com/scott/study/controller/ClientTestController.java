package com.scott.study.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLASSNAME :ClientTestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  10:04
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class ClientTestController {

    Log log = LogFactory.getLog(ClientTestController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        log.info("server被调用了！:" +port);
        return "server被调用了！:" +port;
    }

    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public String Data(@RequestParam("name") String name){
        log.info("传入数据："+name);
        return "传入数据："+name;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String Info(){
        log.info("This is info DemoTest");
        return "This is info DemoTest";
    }
}
