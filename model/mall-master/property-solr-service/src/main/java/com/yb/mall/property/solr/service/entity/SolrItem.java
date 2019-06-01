package com.yb.mall.property.solr.service.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.DecimalFormat;


/*
* @Description: Solr搜索Item
* @author cw
* @date 2019/1/21 10:58
*/
@Setter
@Getter
public class SolrItem implements Serializable {

    private String id;
    private String title;
    private Long price;
    private String image;
    private String category;
    private String brand;
    private String seller;

    public String getPriceView() {
        DecimalFormat df1 = new DecimalFormat("#.00");
        df1.setGroupingUsed(false);
        String format = df1.format(price / 100);
        return format;
    }

}
