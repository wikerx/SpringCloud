/*
 * Powered By zsCat, Since 2014 - 2020
 */
package net.getbang.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.shop.mapper.ProductMapper;
import net.getbang.shop.model.Product;
import net.getbang.shop.service.ProductService;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

	@Resource
	private ProductMapper ProductMapper;

	@Override
	public List<Product> selectProductByFloor(Long id) {
		return ProductMapper.selectProductByFloor(id);
	}

	@Override
	public List<Product> getProductByFloorid(Long tid) {
		// TODO Auto-generated method stub
		return ProductMapper.getProductByFloorid(tid);
	}

/*	@Override
	public PageInfo<Product> selectgoodsListByType(int i, int j, Product g) {
		PageHelper.startPage(i, j);
		List<Product> list = ProductMapper.selectgoodsListByType(g);
		return new PageInfo<Product>(list);
	}*/

	@Override
	public List<Product> selectRepoer() {
		// TODO Auto-generated method stub
		return ProductMapper.selectRepoer();
	}

}
