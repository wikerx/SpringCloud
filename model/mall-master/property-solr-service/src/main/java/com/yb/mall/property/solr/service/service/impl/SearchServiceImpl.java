package com.yb.mall.property.solr.service.service.impl;

import com.yb.mall.common.api.rest.RestResult;
import com.yb.mall.property.solr.service.entity.SolrItem;
import com.yb.mall.property.solr.service.mapper.SearchMapper;
import com.yb.mall.property.solr.service.service.SearchService;
import lombok.extern.apachecommons.CommonsLog;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

/*
 * @Description: 搜索服务实现
 * @author cw
 * @date 2019/1/21 10:42
 */
@Service
@CommonsLog
public class SearchServiceImpl implements SearchService {


    private SearchMapper searchMapper;

    private SolrClient solrClient;

    @Autowired
    public SearchServiceImpl(SearchMapper searchMapper, SolrClient solrClient) {
        this.searchMapper = searchMapper;
        this.solrClient = solrClient;
    }

    /**
     * 导入全部商品索引
     *
     * @return
     */
    @Override
    public ResponseEntity importAllItems() {
        List<SolrItem> solrItemList = searchMapper.getSolrItemList();
        try {
            for (SolrItem solrItem : solrItemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", solrItem.getId());
                document.addField("item_category", solrItem.getCategory());
                document.addField("item_title", solrItem.getTitle());
                document.addField("item_image", solrItem.getImage());
                document.addField("item_price", solrItem.getPrice());
                document.addField("item_brand", solrItem.getBrand());
                document.addField("item_seller", solrItem.getSeller());
                solrClient.add(document);
            }
            solrClient.commit();
            log.info("import success num {" + solrItemList.size() + "}");
        } catch (Exception e) {
            log.error("import error", e);
        }
        return RestResult.success();
    }

}
