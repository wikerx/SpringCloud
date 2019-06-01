package net.getbang.test.address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import net.getbang.shop.mapper.AddressMapper;
import net.getbang.shop.model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddRessTest {

	
	@Autowired
	private  AddressMapper addressMapper;
	
	
	@Test
	public void select_by_id() {
		
		Address address = addressMapper.selectById(21);
		
		System.out.println(JSON.toJSON(address));
		
	}
}
