package com.github.zuihou.file.configuration.datasource;


import javax.sql.DataSource;

import com.github.zuihou.db.configure.BaseDbConfiguration;
import com.github.zuihou.db.configure.DataSourceFactory;
import com.github.zuihou.file.config.FileDataSourceProperties;

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
                "com.github.zuihou.file.repository.file.dao",
        },
        sqlSessionFactoryRef = "sqlSessionFactory_file")
@EnableConfigurationProperties({FileDataSourceProperties.class})
public class FileAutoConfiguration extends BaseDbConfiguration {

    private final FileDataSourceProperties dataSourceProperties;

    @Autowired
    public FileAutoConfiguration(FileDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * 实例DataSource
     *
     * @return
     */
    @Bean(name = "dataSource_file", initMethod = "init", destroyMethod = "close")
    @Primary
    public DataSource rdsDataSource() {
        return DataSourceFactory.createDataSource(this.dataSourceProperties);
    }

    /**
     * 事务管理
     *
     * @return
     */
    @Bean(name = "tx_file")
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
    @Bean(name = "sqlSessionFactory_file")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource_file") DataSource rdsDataSource) throws Exception {
        return DataSourceFactory.createSqlSessionFactoryBean(rdsDataSource, new String[]{"classpath:mapper_file/**/*.xml"}).getObject();
    }

    /**
     * 统一配置事务
     *
     * @param transactionManager
     * @return
     */
    @Bean
    public TransactionInterceptor txAdvice(@Qualifier("tx_file") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean
    public Advisor txAdviceAdvisor(@Qualifier("tx_file") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }


}
