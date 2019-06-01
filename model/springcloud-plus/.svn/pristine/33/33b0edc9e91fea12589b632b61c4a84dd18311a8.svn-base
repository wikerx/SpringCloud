package net.getbang.service.user;



import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import net.getbang.entity.user.User;



public interface IUserService extends IService<User>{
	
	User getUserById(Long id);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
}
