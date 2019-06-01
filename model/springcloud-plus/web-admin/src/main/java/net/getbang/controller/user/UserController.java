package net.getbang.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.getbang.entity.user.User;
import net.getbang.service.user.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUserById")
	public User getUserById(@RequestParam(value = "id") Long id) {
		
		System.out.println("id============"+id);
		return userService.getUserById(id);
	}
	
	
}
