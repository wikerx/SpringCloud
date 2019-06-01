package com.yb.mall.property.solr.service.controller;

import com.yb.mall.property.solr.service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
* @Description: 搜索服务
* @author cw
* @date 2019/1/21 10:45
*/
@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 导入全部商品索引
     * @return
     */
    @RequestMapping(value = "/importAllItems",method = RequestMethod.POST)
    public ResponseEntity importAllItems(){
        return searchService.importAllItems();
    }

    @RequestMapping("/hi")
    public String home(){
        return "hi i'm hi2!";
    }

    @RequestMapping("/his")
    public String his(){
        return restTemplate.getForObject("http://localhost:666666/tbUser/his", String.class);
    }
}
