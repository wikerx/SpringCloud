package com.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        //异常过滤器
        return "error";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        boolean isSuccess = context.get("isSuccess")==null ? true :(boolean)context.get("isSuccess");
        if(!isSuccess){
            String result = (String)context.get("result");
            logger.info("result:{}",result);
            context.setResponseBody(result);
        }
        return null;
    }
}
