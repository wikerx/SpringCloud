package net.getbang.shop.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import net.getbang.common.base.SuperEntity;

@TableName("shop_product_class")
public class ProductClass extends SuperEntity<ProductClass>{
   

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图片
     */
    private String pic;

    /**
     * 前台显示，0为否，1为是，默认为1
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 名称
     */
    private String title;

    @TableField("parent_id")
    private Long parentId;

    @TableField("parent_ids")
    private String parentIds;

    

    public void setName(String name) {
		this.name = name;
	}

	/**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */

    /**
     * 获取分类图片
     *
     * @return pic - 分类图片
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置分类图片
     *
     * @param pic 分类图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取前台显示，0为否，1为是，默认为1
     *
     * @return del_flag - 前台显示，0为否，1为是，默认为1
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置前台显示，0为否，1为是，默认为1
     *
     * @param delFlag 前台显示，0为否，1为是，默认为1
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取名称
     *
     * @return title - 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称
     *
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return parent_ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}