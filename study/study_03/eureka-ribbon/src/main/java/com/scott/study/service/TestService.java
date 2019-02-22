package com.scott.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2019/2/22 0022.
 */
@Service
public class TestService {
    @Autowired
    RestTemplate restTemplate;

    public String testService() {
        return restTemplate.getForObject("http://SERVICE-RIBBON/test", String.class);
//        return restTemplate.getForObject("http://STUDY/test", String.class);
    }
}
