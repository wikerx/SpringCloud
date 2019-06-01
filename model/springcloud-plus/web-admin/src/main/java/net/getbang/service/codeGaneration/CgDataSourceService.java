package net.getbang.service.codeGaneration;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*import com.baomidou.mybatisplus.plugins.Page;*/

import net.getbang.entity.codeGaneration.CgDataSource;
@FeignClient(value = "service-code-ganeration")
public interface CgDataSourceService{

	@RequestMapping(value="cg/datasource/getDataSourceList",method = RequestMethod.GET)
	List<CgDataSource> getDataSourceList(@RequestParam(value="params")Map<String,Object> params);
	/*@RequestMapping(value="cg/datasource/pageList",method = RequestMethod.GET)
	Page<CgDataSource> pageList(@RequestParam("params")Map<String,Object> params);*/
}
