package com.github.zuihou.config.datasource;


import javax.sql.DataSource;


import com.github.zuihou.db.configure.BaseDbConfiguration;
import com.github.zuihou.db.configure.DataSourceFactory;
import com.github.zuihou.db.configure.DataSourceProperties;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 一体化平台 中心数据库配置
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@MapperScan(
        basePackages = {
                "com.github.zuihou",
        },
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "sqlSessionFactory_msgs")
@EnableConfigurationProperties({MsgsAutoConfiguration.CenterDataSourceProperties.class})
@Slf4j
public class MsgsAutoConfiguration extends BaseDbConfiguration {

    private final CenterDataSourceProperties dataSourceProperties;

    public MsgsAutoConfiguration(CenterDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean(name = "dataSource_msgs", initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource rdsDataSource() {
        return DataSourceFactory.createDataSource(this.dataSourceProperties);
    }

    @Bean(name = "tx_msgs")
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(rdsDataSource());
    }

    @Bean(name = "sqlSessionFactory_msgs")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource_msgs") DataSource rdsDataSource) throws Exception {
        return DataSourceFactory.createSqlSessionFactoryBean(rdsDataSource, new String[]{
                "classpath:mapper_msgs/**/*.xml",
                "classpath:mapper_sms/**/*.xml",
        },profiles).getObject();
    }

    @Bean
    public TransactionInterceptor txAdvice(@Qualifier("tx_msgs") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean
    public Advisor txAdviceAdvisor(@Qualifier("tx_msgs") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }

    @Override
    protected Logger getLog() {
        return this.log;
    }

    @ConfigurationProperties(
            prefix = "zuihou.mysql.msgs"
    )
    static class CenterDataSourceProperties extends DataSourceProperties {

    }
}
