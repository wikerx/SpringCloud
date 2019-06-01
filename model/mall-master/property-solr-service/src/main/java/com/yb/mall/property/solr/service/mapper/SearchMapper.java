package com.yb.mall.property.solr.service.mapper;

import com.yb.mall.property.solr.service.entity.SolrItem;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* @Description: Solr操作Mapper
* @author cw
* @date 2019/1/21 10:57
*/
@Component
public interface SearchMapper {

    /**
     * 获取全部商品
     * @return
     */
    List<SolrItem> getSolrItemList();

    /**
     * 通过Id查询商品
     * @param id
     * @return
     */
    SolrItem getSolrItemByItemId(long id);
}
