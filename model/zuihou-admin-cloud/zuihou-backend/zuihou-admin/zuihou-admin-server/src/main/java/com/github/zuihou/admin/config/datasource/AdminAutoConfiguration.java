package com.github.zuihou.admin.config.datasource;


import javax.sql.DataSource;

import com.github.zuihou.admin.configuration.AdminDataSourceProperties;
import com.github.zuihou.db.configure.BaseDbConfiguration;
import com.github.zuihou.db.configure.DataSourceFactory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 统一平台 中心数据库配置
 * 这里自定义数据库链接的目的是为了后期扩展 多数据源比较方便
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@EnableCaching
@MapperScan(
        basePackages = {
                "com.github.zuihou.admin.repository.account.dao",
                "com.github.zuihou.admin.repository.authority.dao",
        },
        sqlSessionFactoryRef = "sqlSessionFactory_admin")
@EnableConfigurationProperties({AdminDataSourceProperties.class})
public class AdminAutoConfiguration extends BaseDbConfiguration {

    private final AdminDataSourceProperties dataSourceProperties;

    @Autowired
    public AdminAutoConfiguration(AdminDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * 实例DataSource
     *
     * @return
     */
    @Bean(name = "dataSource_admin", initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource rdsDataSource() {
        return DataSourceFactory.createDataSource(this.dataSourceProperties);
    }

    /**
     * 事务管理
     *
     * @return
     */
    @Bean(name = "tx_admin")
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(rdsDataSource());
    }

    /**
     * sql链接工厂
     *
     * @param rdsDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory_admin")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource_admin") DataSource rdsDataSource) throws Exception {
        return DataSourceFactory.createSqlSessionFactoryBean(rdsDataSource, new String[]{"classpath:mapper_admin/**/*.xml"}).getObject();
    }

    /**
     * 统一配置事务
     *
     * @param transactionManager
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice(@Qualifier("tx_admin") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean
    public Advisor txAdviceAdvisor(@Qualifier("tx_admin") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }


}
