package com.yb.mall.cloud.zuul.server.fiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class CertificationFilter extends ZuulFilter {
    private static final Log logger = LogFactory.getLog(CertificationFilter.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        // token
        String token = request.getHeader("Authorization");
        String urlString = request.getRequestURI();
        // token空且不在放行列表中
        // TODO: 待优化，待完善
        if (StringUtils.isEmpty(token)) {
            // swagger
            if (urlString.contains("/swagger-ui.html")
                    || urlString.contains("/webjars/")
                    || urlString.contains("/swagger-resources")
                    || urlString.contains("/v2/")) {
                return null;
            }
            // consul
            if (urlString.contains("/consul/")) {
                return null;
            }
            // 搜索子系统
            if (urlString.contains("/property-search-service/")) {
                return null;
            }

            // 商品客户端业务子系统
            if (urlString.contains("/property-client-service/")) {
                return null;
            }

            this.verifyTokenFailure(response, context);
            return null;
        }
        // token不为空且认证失败
        if (StringUtils.isNotEmpty(token)) {
            this.verifyTokenFailure(response, context);
            return null;
        }

        return null;
    }

    /**
     * token认证失败
     *
     * @param response {@link HttpServletResponse}
     * @param context  {@link RequestContext}
     */
    private void verifyTokenFailure(HttpServletResponse response, RequestContext context) {
        logger.info("token认证失败");
        context.setSendZuulResponse(false);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        context.setResponse(response);
        context.setResponseStatusCode(401);// 返回错误码
        context.setResponseBody("{\"data\":\"用户认证失败\"}");// 返回错误内容
    }

}