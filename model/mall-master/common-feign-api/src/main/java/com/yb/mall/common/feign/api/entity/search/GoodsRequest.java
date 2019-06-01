package com.yb.mall.common.feign.api.entity.search;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 * @Description: 商品
 * @author cw
 * @date 2019/1/17 17:26
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsRequest implements Serializable{

    private Long id; //商品ID
    private String title; //商品标题
    private BigDecimal price; //商品价格
    private String image; //商品图片
    private String category; //商品类别
    private String brand; //商品品牌
    private String seller; //商品卖家

    public GoodsRequest(Long id, String title, BigDecimal price, String category, String brand, String seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.seller = seller;
    }


}
