package net.getbang.codeGaneration;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import net.getbang.entity.codeGaneration.CgDataSource;
import net.getbang.mapper.codeGaneration.CgDataSourceMapper;
import net.getbang.service.codeGaneration.CgDataSourceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceTest {

	@Autowired
	private CgDataSourceService cgDataSourceService;
	@Autowired
	private CgDataSourceMapper cgDataSourceMapper;
	@Test
	public void getDatesouceTestPage() {
		
		Page<CgDataSource> page = new Page<CgDataSource>();
		
		Page<CgDataSource> page2 = cgDataSourceService.selectPage(page);
		
		
		
		Page<CgDataSource> page3 = cgDataSourceService.SelectCgDataSourceListPage(page, null);
		
		Page<CgDataSource> page4 = cgDataSourceService.selectPage(page, null);
		
		System.out.println(JSON.toJSONString(page2));
		
		System.out.println(JSON.toJSONString(page3));
		
		System.out.println(JSON.toJSONString(page4));
		
	}
	@Test
	public void getDatesouceTest() {
		
		
		CgDataSource cs = cgDataSourceMapper.getbyid();
		
		System.out.println(cs);
		
	}
	
	
}
