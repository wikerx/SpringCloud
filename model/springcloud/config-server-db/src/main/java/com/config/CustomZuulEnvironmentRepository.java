package com.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Profile("custom")
@ConfigurationProperties("spring.cloud.config.server.custom")
public class CustomZuulEnvironmentRepository implements EnvironmentRepository, Ordered {
    protected static final Logger LOGGER = LoggerFactory.getLogger(CustomZuulEnvironmentRepository.class);

    public CustomZuulEnvironmentRepository(){
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    private int order = Ordered.LOWEST_PRECEDENCE;

    @Override
    public Environment findOne(String application, String profile, String label) {
        LOGGER.info("加载interfacecode表...");

        Map<String, Object> sourceMap = new ConcurrentHashMap<>();
        if (StringUtils.isEmpty(application))
            application = "zuul-server";
        if (StringUtils.isEmpty(profile))
            profile = "dev,test,prod";
        if (StringUtils.isEmpty(label))
            label = "master";

        String sql = "select id, beanid, beanclass, interfacecode, `keys` from interfacecode ";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String interfacecode = rs.getString("interfacecode");

                InterfaceCode interfaceCode = new InterfaceCode();
                interfaceCode.setId(rs.getInt("id"));
                interfaceCode.setBeanid(rs.getString("beanid"));
                interfaceCode.setBeanclass(rs.getString("beanclass"));
                interfaceCode.setInterfacecode(interfacecode);
                 interfaceCode.setKeys(rs.getString("keys"));

                sourceMap.put(interfacecode, JSON.toJSONString(interfaceCode));
            }
        });
        LOGGER.info("加载完成...size:{}", sourceMap.size());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        Environment environment = new Environment(application, StringUtils.commaDelimitedListToStringArray(profile), label, null, null);
        PropertySource propertySource = new PropertySource("custom", sourceMap);
        environment.add(propertySource);
        LOGGER.info("add to environment success...");
        return environment;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

