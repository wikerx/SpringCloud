package net.getbang.service.menu;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.getbang.entity.system.SysMenu;


@FeignClient(value = "service-user")
public interface SysMenuService {
	
	@RequestMapping(value="sys/menu/getUserMenuList",method = RequestMethod.GET)
	List<SysMenu>  getUserMenuList(@RequestParam(value="userId") Long userId);
	
	@RequestMapping(value="sys/menu/queryMenuList",method = RequestMethod.GET)
	List<SysMenu>  queryMenuList(@RequestParam(value="params") Map<String, Object> params);
}
