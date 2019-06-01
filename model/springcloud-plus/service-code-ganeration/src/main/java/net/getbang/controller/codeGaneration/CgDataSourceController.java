package net.getbang.controller.codeGaneration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import net.getbang.entity.codeGaneration.CgDataSource;
import net.getbang.service.codeGaneration.CgDataSourceService;

@RestController
@RequestMapping("cg/datasource")
public class CgDataSourceController {

	
	@Autowired
	private CgDataSourceService cgDataSourceService;
	
	@GetMapping(value="getDataSourceList")
	public List<CgDataSource> getDataSourceList(Map<String,Object> columnMap){
		
		List<CgDataSource> list = cgDataSourceService.selectByMap(columnMap);
		
		return list;
	}
	
	@GetMapping(value="pageList")
	public Page<CgDataSource> pageList(Page<CgDataSource> page ,Map<String,Object> columnMap){
		
		
		return cgDataSourceService.selectPage(page);
	}
	
	
}
