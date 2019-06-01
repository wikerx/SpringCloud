package net.getbang.mapper.system;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import net.getbang.entity.system.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu>{
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListByParentId(Long parentId);
}
