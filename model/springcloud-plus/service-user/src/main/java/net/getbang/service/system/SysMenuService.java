package net.getbang.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import net.getbang.entity.system.SysMenu;

public interface SysMenuService extends IService<SysMenu>{

	List<SysMenu> getUserMenuList(Long userId);
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListByParentId(Long parentId);
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> queryListByParentId(Long parentId, List<Long> menuIdList);
	
	
	List<SysMenu> queryList(Map<String, Object> hashMap);
}
