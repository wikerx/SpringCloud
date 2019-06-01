/** Powered By zscat科技, Since 2016 - 2020 */

package net.getbang.shop.model;

import com.baomidou.mybatisplus.annotations.TableName;

import net.getbang.common.base.SuperEntity;

/**
 * 
 * @author zsCat 2017-1-7 16:07:35
 * @Email: 951449465@qq.com
 * @version 1.0v 商品订单管理
 */
@SuppressWarnings({ "unused" })
@TableName( "shop_goodsorder")
public class GoodsOrder extends SuperEntity<GoodsOrder> {

	private static final long serialVersionUID = 1L;

	private Long goodsid;

	private Long orderid;

	public Long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

}
