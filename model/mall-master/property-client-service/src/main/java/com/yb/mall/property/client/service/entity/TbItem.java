package com.yb.mall.property.client.service.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cw
 * @since 2019-02-13
 */
@TableName("tb_item")
public class TbItem extends Model<TbItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品价格
     */
    private String price;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品标题
     */
    private String category;
    /**
     * 商品品牌
     */
    private String brand;
    /**
     * 商品卖家
     */
    private String seller;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbItem{" +
        ", id=" + id +
        ", title=" + title +
        ", price=" + price +
        ", image=" + image +
        ", category=" + category +
        ", brand=" + brand +
        ", seller=" + seller +
        "}";
    }
}
