 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;


import net.getbang.shop.model.Cart;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
public interface CartService extends IService<Cart>  {

	List<Cart> selectOwnCart(Long uid);

	/*int selectOwnCartCount(Long uid);*/


}
