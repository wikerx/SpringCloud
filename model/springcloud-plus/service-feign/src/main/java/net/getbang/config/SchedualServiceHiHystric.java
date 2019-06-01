package net.getbang.config;

import org.springframework.stereotype.Component;

import net.getbang.service.SchedualServiceHi;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{

	@Override
	public String sayHiFromClientOne(String name) {
		 return "sorry "+name;
	}

}
