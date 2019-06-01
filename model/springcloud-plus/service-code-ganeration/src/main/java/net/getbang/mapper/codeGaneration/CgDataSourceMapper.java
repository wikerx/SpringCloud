package net.getbang.mapper.codeGaneration;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import net.getbang.entity.codeGaneration.CgDataSource;
@Repository
public interface CgDataSourceMapper extends BaseMapper<CgDataSource>{

	
	List<CgDataSource> SelectCgDataSourceListPage(Pagination page,Map<String,Object> columnMap);
	
	
	CgDataSource getbyid();
}
