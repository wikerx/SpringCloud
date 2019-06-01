package net.getbang.system;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.getbang.entity.system.SysMenu;
import net.getbang.mapper.system.SysMenuMapper;
import net.getbang.service.system.SysMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuTest {

	@Autowired
	private  SysMenuMapper sysMenuMapper;
	
	@Autowired 
	SysMenuService sysMenuService;
	@Test
	public void getmenu() {
		
		SysMenu sm = 	sysMenuMapper.selectById(1);
		
		List<SysMenu> menuList = sysMenuService.queryList(new HashMap<String, Object>());
		
		
		System.out.println(sm.getName());
		
		System.out.println(menuList);
		
	}
	
	
}
