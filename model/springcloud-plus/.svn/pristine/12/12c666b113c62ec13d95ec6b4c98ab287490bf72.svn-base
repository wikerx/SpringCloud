package net.getbang.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.getbang.common.entity.Result;
import net.getbang.entity.system.SysMenu;
import net.getbang.service.system.SysMenuService;

@RestController
@RequestMapping("sys/menu")
public class SysMenuController {

	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 导航菜单
	 */
	@RequestMapping(value="/getUserMenuList")
	public List<SysMenu> getUserMenuList(@RequestParam Long userId){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(userId);
		//Set<String> permissions = sysUserService.getUserPermissions(getUserId());
		return menuList;
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping(value="/queryMenuList")
	public List<SysMenu> queryMenuList(@RequestParam Map<String, Object> params){
		List<SysMenu> menuList = sysMenuService.queryList(params);

		return menuList;
	}
	
}
