package net.getbang.shop.model;

import java.util.Date;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import net.getbang.common.base.SuperEntity;

@TableName( "shop_product_type")
public class ProductType extends SuperEntity<ProductType>{
   

    private String name;

    /**
     * 创建者
     */
    @TableField( "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField( "create_date")
    private Date createDate;

    /**
     * 删除标记(0或null 正常 1,删除)
     */
    @TableField( "del_flag")
    private String delFlag;

    

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    
    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public Long getCreateBy() {
        return createBy;
    }

    public void setName(String name) {
		this.name = name;
	}

	/**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取删除标记(0活null 正常 1,删除)
     *
     * @return del_flag - 删除标记(0活null 正常 1,删除)
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记(0活null 正常 1,删除)
     *
     * @param delFlag 删除标记(0活null 正常 1,删除)
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}