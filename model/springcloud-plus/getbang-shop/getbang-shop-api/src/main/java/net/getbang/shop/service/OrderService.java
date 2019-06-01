 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service;

import com.baomidou.mybatisplus.service.IService;

import net.getbang.shop.model.Order;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
public interface OrderService extends IService<Order>  {

	 Order insertOrder(String[] cartIds,Long addressid, Long paymentid, String usercontent,Long uid,String uname) ;


	Order insertWapOrder(Long productId, Long addressid, Long paymentid, String usercontent, Long id, String username);
}
