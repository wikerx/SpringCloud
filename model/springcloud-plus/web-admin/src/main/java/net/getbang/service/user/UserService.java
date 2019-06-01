package net.getbang.service.user;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.getbang.entity.user.User;


@FeignClient(value = "service-user")
public interface UserService {

	@RequestMapping(value="user/getUserById",method = RequestMethod.GET)
	User getUserById(@RequestParam(value = "id") Long id);
	
	/***查询系统用户的菜单id*/
	@RequestMapping(value="user/queryAllMenuId",method = RequestMethod.GET)
	List<Long> queryAllMenuId(@RequestParam(value="userId") Long userId);
}
