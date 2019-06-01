 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.shop.mapper.CartMapper;
import net.getbang.shop.model.Cart;
import net.getbang.shop.service.CartService;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {
	@Resource
	private CartMapper CartMapper;

	 @Override
		public List<Cart> selectOwnCart(Long uid) {
			
				Map<String, Object> columnMap = new HashMap<>();
				columnMap.put("userid", uid);
				return CartMapper.selectByMap(columnMap);
		}
		/*@Override
		public int selectOwnCartCount(Long uid) {
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("userid", uid);
			
			return CartMapper.selectCount(wrapper);

			
		}*/

  
    
}
