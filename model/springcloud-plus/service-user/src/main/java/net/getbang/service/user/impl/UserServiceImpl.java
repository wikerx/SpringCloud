package net.getbang.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.entity.user.User;
import net.getbang.mapper.user.UserMapper;
import net.getbang.service.user.IUserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public User getUserById(Long id) {
		return  userMapper.selectById(id);
	}
	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return userMapper.queryAllMenuId(userId);
	}
}
