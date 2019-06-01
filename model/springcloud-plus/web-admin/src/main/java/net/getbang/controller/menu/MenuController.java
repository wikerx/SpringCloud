package net.getbang.controller.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.getbang.common.entity.Result;
import net.getbang.entity.system.SysMenu;
import net.getbang.service.menu.SysMenuService;
import net.getbang.service.user.UserService;

@RestController
@RequestMapping("/sys/menu")
public class MenuController {

	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	public Result nav(){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(1l);
		return Result.ok().put("menuList", menuList);
	}
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	public List<SysMenu> list(Map<String, Object> params){
		
		List<SysMenu> menuList = sysMenuService.queryMenuList(params);

		return menuList;
	}
}
