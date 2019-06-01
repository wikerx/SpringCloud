package net.getbang.controller.codeGaneration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*import com.baomidou.mybatisplus.plugins.Page;*/

import net.getbang.entity.codeGaneration.CgDataSource;
import net.getbang.service.codeGaneration.CgDataSourceService;

@RestController
@RequestMapping("cg/datasource")
public class CgDataSourceController {

	
	@Autowired
	private CgDataSourceService cgDataSourceService;
	
	@GetMapping(value="list")
	public List<CgDataSource> list(Map<String,Object> columnMap){
		
		List<CgDataSource> list = cgDataSourceService.getDataSourceList(columnMap);
		
		return list;
	}
	/*@GetMapping(value="pageList")
	public Page<CgDataSource> pageList(Map<String,Object> columnMap){
		
		return cgDataSourceService.pageList(columnMap);
	}*/
	
}
