package net.getbang.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.common.entity.Constant.MenuType;
import net.getbang.entity.system.SysMenu;
import net.getbang.mapper.system.SysMenuMapper;
import net.getbang.service.system.SysMenuService;
import net.getbang.service.user.IUserService;
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService{

	@Autowired
	private IUserService iUserService;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Override
	public List<SysMenu> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
				if(userId == 1){
					return getAllMenuList(null);
				}
				//用户菜单列表
				List<Long> menuIdList = iUserService.queryAllMenuId(userId);
				return getAllMenuList(menuIdList);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenu> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenu> menuList = queryListByParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}
	/**
	 * 递归
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			if(entity.getType() == MenuType.CATALOG.getValue()){//目录
			
				entity.setList(getMenuTreeList(queryListByParentId(entity.getId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
	@Override
	public List<SysMenu> queryListByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return sysMenuMapper.queryListByParentId(parentId);
	}

	@Override
	public List<SysMenu> queryListByParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenu> menuList = queryListByParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryList(Map<String, Object> hashMap) {
		return sysMenuMapper.selectByMap(hashMap);
	}

	
}
