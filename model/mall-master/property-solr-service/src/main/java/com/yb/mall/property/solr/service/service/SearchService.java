package com.yb.mall.property.solr.service.service;

import org.springframework.http.ResponseEntity;

/*
* @Description: 搜索
* @author cw
* @date 2019/1/21 10:41
*/
public interface SearchService {

    /**
     * 导入全部商品索引
     */
    ResponseEntity importAllItems();
}
