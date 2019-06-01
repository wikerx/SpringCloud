package net.getbang.shop.model;

import net.getbang.common.base.SuperEntity;


import com.baomidou.mybatisplus.annotations.TableName;


@TableName("shop_article")
public class Article extends SuperEntity<Article> {
   

    private String title;
    private String type;
    private String remark;
    private String name;
    private String del_flag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }
}