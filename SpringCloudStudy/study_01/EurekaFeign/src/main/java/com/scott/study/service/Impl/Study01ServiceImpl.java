package com.scott.study.service.Impl;

import com.scott.study.service.Study01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @CLASSNAME :Study01ServiceImpl
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/8 0008  11:56
 * @Version :V1.0
 * @Status : 编写
 **/
@Service
public class Study01ServiceImpl implements Study01Service{

    @Autowired
    Study01Service study01Service;

    /*测试*/
    public String testStudyNo1(@RequestParam("name")String name){
        return study01Service.testStudyNo1(name);
    }
}
