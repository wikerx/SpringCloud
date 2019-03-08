package com.scott.study.service.Impl;

import com.scott.study.service.Study02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class Study02ServiceImpl implements Study02Service{

    @Autowired
    Study02Service study02Service;

    /*测试*/
    public String testStudyNo2(@RequestParam("name")String name){
        return study02Service.testStudyNo2(name);
    }
}
