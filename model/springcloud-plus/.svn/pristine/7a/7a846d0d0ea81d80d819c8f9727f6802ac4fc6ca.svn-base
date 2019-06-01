package net.getbang.service.codeGaneration.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import net.getbang.entity.codeGaneration.CgDataSource;
import net.getbang.mapper.codeGaneration.CgDataSourceMapper;
import net.getbang.service.codeGaneration.CgDataSourceService;
@Service
public class CgDataSourceServiceImpl extends ServiceImpl<CgDataSourceMapper,CgDataSource> implements CgDataSourceService{

	@Resource
	private CgDataSourceMapper cgDataSourceMapper;
	@Override
	public Page<CgDataSource> SelectCgDataSourceListPage(Page<CgDataSource> page, Map<String,Object> columnMap) {
		// TODO Auto-generated method stub
		return page.setRecords(cgDataSourceMapper.SelectCgDataSourceListPage(page, columnMap));
			
	}

}
