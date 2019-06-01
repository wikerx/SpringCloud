package net.getbang.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class MyFilter extends ZuulFilter{

	@Override
	public Object run() {
		// TODO 过滤器的具体逻辑
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// 这里可以写逻辑判断，是否要过滤 false代码不需要，true代码需要
		return false;
	}

	@Override
	public int filterOrder() {
		// TODO 过滤的顺序
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return null;
	}

}
