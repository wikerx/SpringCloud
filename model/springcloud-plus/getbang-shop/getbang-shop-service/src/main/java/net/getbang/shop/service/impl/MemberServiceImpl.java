 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package net.getbang.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.common.utils.PasswordEncoder;
import net.getbang.shop.mapper.MemberMapper;
import net.getbang.shop.model.Member;
import net.getbang.shop.service.MemberService;


 /**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,Member> implements MemberService {

	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Member checkUser(String username, String password) {
		Member sysUser = new Member();
		String secPwd = PasswordEncoder.encrypt(password, username);
		sysUser.setUsername(username);
		sysUser.setPassword(secPwd);
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("username", username);
		columnMap.put("password",secPwd);
		List<Member> users = memberMapper.selectByMap(columnMap);
		if(users != null && users.size() == 1 && users.get(0) != null) {
			return users.get(0);
		}
		return null;
	}

  
    
}
