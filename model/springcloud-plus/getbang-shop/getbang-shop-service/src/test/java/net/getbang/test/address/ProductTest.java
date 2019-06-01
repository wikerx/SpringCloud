package net.getbang.test.address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.getbang.shop.mapper.ProductMapper;
import net.getbang.shop.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	@Autowired
	private ProductMapper productMapper;
	
	@Test
	public void selectbyid() {
		
		
		Product product = productMapper.selectById(55);
		
		System.out.println(product.getTitle());
	}
	
}
