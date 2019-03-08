package com.scott.study.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2019/3/8 0008.
 */
public interface HystrixService {

    String success();

    String isError();

}
