package com.scott.study.controller;

import com.scott.study.service.Study01Service;
import com.scott.study.service.Study02Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @CLASSNAME :TestController
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  14:27
 * @Version :V1.0
 * @Status : 编写
 **/
@RestController
public class TestController {
    Log log = LogFactory.getLog(TestController.class);

    @Autowired
    Study01Service study01Service;
    @Autowired
    Study02Service study02Service;

//    @GetMapping("/test")//不能用get哟
    @RequestMapping(value = "/test1/{name}",method = RequestMethod.GET)
    public String Test1(@PathVariable(value = "name")String name){
        log.info("----------test------"+name);
        return study01Service.testStudyNo1(name);
    }

    @RequestMapping(value = "/test2/{name}",method = RequestMethod.GET)
    public String Test2(@PathVariable(value = "name")String name){
        log.info("----------test------"+name);
        return study02Service.testStudyNo2(name);
    }

    /**
     * 说明：
     * http://localhost:8766/test1/M  来回显示：8763 8762 两个端口的调用显示，说明此时使用的负载型
     * http://localhost:8766/test2/M  仅仅显示 8765 一个端口的调用现实，说明此时未使用负载
     * 以上信息对比说明@FeignClient 的使用
     * FeignClient 会将不同的数据定位道不同的注册中心上面
    **/




}
