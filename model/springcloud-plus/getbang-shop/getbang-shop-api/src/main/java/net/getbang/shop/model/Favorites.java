package net.getbang.shop.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import net.getbang.common.base.SuperEntity;
/**
 * 收藏
 * @author Administrator
 *
 */

@TableName( "shop_favorites")
public class Favorites extends SuperEntity<Favorites>{
   

    /**
     * 收藏ID
     */
    @TableField( "fav_id")
    private Long favId;

    /**
     * 收藏类型
     */
    @TableField( "fav_type")
    private String favType;

    /**
     * 收藏时间
     */
    @TableField( "fav_time")
    private Long favTime;

    @TableField( "member_id")
    private Long memberId;

    

    /**
     * 获取收藏ID
     *
     * @return fav_id - 收藏ID
     */
    public Long getFavId() {
        return favId;
    }

    /**
     * 设置收藏ID
     *
     * @param favId 收藏ID
     */
    public void setFavId(Long favId) {
        this.favId = favId;
    }

    /**
     * 获取收藏类型
     *
     * @return fav_type - 收藏类型
     */
    public String getFavType() {
        return favType;
    }

    /**
     * 设置收藏类型
     *
     * @param favType 收藏类型
     */
    public void setFavType(String favType) {
        this.favType = favType;
    }

    /**
     * 获取收藏时间
     *
     * @return fav_time - 收藏时间
     */
    public Long getFavTime() {
        return favTime;
    }

    /**
     * 设置收藏时间
     *
     * @param favTime 收藏时间
     */
    public void setFavTime(Long favTime) {
        this.favTime = favTime;
    }

    /**
     * @return member_id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}