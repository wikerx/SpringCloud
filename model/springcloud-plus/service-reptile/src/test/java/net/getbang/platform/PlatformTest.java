package net.getbang.platform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import net.getbang.entity.paltform.Platform;
import net.getbang.mapper.platform.PlatformMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformTest {

	@Autowired
	private PlatformMapper paltformMapper;
	
	@Test
	public void getPlatform() {
		
		
		Platform  p = paltformMapper.selectById(1);
		
		System.out.println(p.getpName());
	}
	
	
	
}
