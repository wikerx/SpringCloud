 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
//import com.github.pagehelper.PageInfo;

import net.getbang.shop.model.Product;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
public interface ProductService extends IService<Product>  {

	public List<Product> selectProductByFloor(Long id);
	public List<Product> getProductByFloorid(Long tid);
	// PageInfo<Product> selectgoodsListByType(int i, int j, Product g);
	public List<Product> selectRepoer();



}
