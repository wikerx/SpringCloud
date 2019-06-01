 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.shop.mapper.AddressMapper;
import net.getbang.shop.model.Address;
import net.getbang.shop.service.AddressService;


 @Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper,Address> implements AddressService {

	@Override
	public List<Address> selectByMemberId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateDef(String addressId, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

  
    
}
