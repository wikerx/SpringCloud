package com.github.zuihou.commons.adapter;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

/**
 * 动态的给swagger添加参数
 * 详情参见：
 * https://github.com/cyjishuang/swagger-mode
 * https://blog.csdn.net/hellopeng1/article/details/82227942
 * https://www.jianshu.com/p/fe58f7457e38
 * http://blog.51cto.com/7308310/2082742
 *
 * @author zuihou
 * @date 2018/11/22
 */

//@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class MyOperationBuilderPlugin implements OperationBuilderPlugin {
    @Override
    public void apply(OperationContext context) {
        context.operationBuilder().parameters(readParameters(context));
    }

    private boolean isIgnoreToken;
    private String profiles;

    public MyOperationBuilderPlugin(boolean isIgnoreToken, String profiles) {
        this.isIgnoreToken = isIgnoreToken;
        this.profiles = profiles;
    }

    public MyOperationBuilderPlugin() {
        this.isIgnoreToken = false;
    }

    private List<Parameter> readParameters(OperationContext context) {
        List<Parameter> parameters = Lists.newArrayList();

        if (isIgnoreToken) {
            return parameters;
        }
        String currentUri = context.requestMappingPattern();
        boolean isIgnore = IgnoreTokenConfig.isIgnoreToken(currentUri);
        boolean isIgnoreUser = IgnoreTokenConfig.isIgnoreUserToken(currentUri);

        if (!isIgnore) {
            if (!isIgnoreUser) {
                Parameter parameter = new ParameterBuilder().name("user-token").description("用户级token令牌")
                        .modelRef(new ModelRef("string")).parameterType("header").required(true).build();
                parameters.add(parameter);
            }
        }

        if (!"prod".equals(profiles)) {
            Parameter parameter = new ParameterBuilder().name("service-suffix").description("想要路由的服务后缀")
                    .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
            parameters.add(parameter);
        }

        return parameters;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
