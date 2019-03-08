package com.scott.study.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2019/3/8 0008.
 */
@FeignClient(value = "studyNo1")//调用那个服务
public interface Study01Service {
    /*测试*/
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String testStudyNo1(@RequestParam(value = "name")String name);
}
