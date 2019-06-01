package net.getbang.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.getbang.entity.user.User;
import net.getbang.service.user.IUserService;

@RestController
@RequestMapping("user")
public class UserController {

	
	
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping(value="/getUserById")
	public User getUserById(@RequestParam Long id) {
		
		
		return iUserService.getUserById(id);
	}
	
	@RequestMapping(value="/queryAllMenuId")
	public List<Long> queryAllMenuId(@RequestParam Long userId) {
		
		return iUserService.queryAllMenuId(userId);
		
	}
	
}
