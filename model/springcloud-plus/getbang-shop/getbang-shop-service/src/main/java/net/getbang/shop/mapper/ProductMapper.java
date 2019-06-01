package net.getbang.shop.mapper;

import java.util.List;

import net.getbang.common.base.SuperMapper;
import net.getbang.shop.model.Product;

public interface ProductMapper extends SuperMapper<Product> {


	public List<Product> selectProductByFloor(Long id);

	public List<Product> getProductByFloorid(Long tid);

	public List<Product> selectgoodsListByType(Product g);

	public List<Product> selectRepoer();
}