/*
 * Powered By zsCat, Since 2014 - 2020
 */
package net.getbang.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.common.utils.RandomString;
import net.getbang.shop.mapper.CartMapper;
import net.getbang.shop.mapper.GoodsOrderMapper;
import net.getbang.shop.mapper.OrderLogMapper;
import net.getbang.shop.mapper.OrderMapper;
import net.getbang.shop.mapper.ProductMapper;
import net.getbang.shop.model.Cart;
import net.getbang.shop.model.GoodsOrder;
import net.getbang.shop.model.Order;
import net.getbang.shop.model.OrderLog;
import net.getbang.shop.model.Product;
import net.getbang.shop.service.OrderService;


/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private GoodsOrderMapper goodsOrderMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private CartMapper cartMapper;
	@Resource
	private OrderLogMapper orderLogMapper;

	@Override
	public Order insertOrder(String[] cartIds, Long addressid, Long paymentid, String usercontent, Long uid,
			String uname) {
		Order order = new Order();

		if (cartIds != null && cartIds.length > 0) {
			int count = 0;
			BigDecimal total = BigDecimal.ZERO;
			for (String cartId : cartIds) {
				// Cart cart=CartMapper.selectByPrimaryKey(Long.parseLong(cartId));
				Cart cart = cartMapper.selectById(cartId);
				if (cart == null) {
					return null;
				}
				count += cart.getCount();
				total = total.add(BigDecimal.valueOf(Double.valueOf(cart.getPrice()))
						.multiply(BigDecimal.valueOf(cart.getCount())));
				GoodsOrder go = new GoodsOrder();
				go.setGoodsid(cart.getGoodsid());
				go.setOrderid(order.getId());
				// GoodsOrderMapper.insertSelective(go);

				// CartMapper.deleteByPrimaryKey(cart);

				cartMapper.deleteById(cartId);
			}
			order.setOrdersn(RandomString.generateRandomString(8));
			order.setCreatedate(new Date());
			order.setStatus(1);
			order.setUserid(uid);
			order.setUsername(uname);
			order.setPaymentid(paymentid);
			order.setUsercontent(usercontent);
			order.setAddressid(addressid);
			// order.setOrderTotalPrice();
			orderMapper.insert(order);

			OrderLog log = new OrderLog();
			log.setOrderId(order.getId());
			log.setOrderState("1");
			log.setStateInfo("提交订单");
			log.setCreateTime(new Date().getTime());
			log.setOperator(uname);
			orderLogMapper.insert(log);

			order.setTotalcount(count);
			order.setTotalprice(total);
			orderMapper.updateById(order);
		}
		return order;

	}

	@Override
	public Order insertWapOrder(Long productId, Long addressid, Long paymentid, String usercontent, Long uid,
			String uname) {
		Order order = new Order();
		Product p = productMapper.selectById(productId);
		order.setOrdersn(RandomString.generateRandomString(8));
		order.setCreatedate(new Date());
		order.setStatus(0);
		order.setUserid(uid);
		order.setUsername(uname);
		order.setPaymentid(paymentid);
		order.setUsercontent(usercontent);
		order.setAddressid(addressid);
		order.setTotalcount(1);
		order.setTotalprice(BigDecimal.valueOf(Double.valueOf(p.getPrices())));
		orderMapper.insert(order);

		OrderLog log = new OrderLog();
		log.setOrderId(order.getId());
		log.setOrderState("1");
		log.setStateInfo("提交订单");
		log.setCreateTime(new Date().getTime());
		log.setOperator(uname);
		orderLogMapper.insert(log);

		return order;
	}

}
