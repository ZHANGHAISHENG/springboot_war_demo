package com.hamlt.springboot;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * 多数据源配置
 * 只要声明了一个数据源，默认的数据源就不会生成
 * 如果@transational 方法使用的事自定义声明的数据源，需要指定事务管理器
 * 如果不指定默认事务管理器，可以不继承TransactionManagementConfigurer接口
 */
@Configuration
public class DataSourceConfig  implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("txManager1")
    private PlatformTransactionManager defaultTxManager;

    @Bean(name = "primaryDataSource") //相当于 @Bean @Qualifier("primaryDataSource")
    //@Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "secondaryDataSource")
    //@Qualifier("secondaryDataSource")
    //@Primary  // @Primary注解的实例优先于其他实例被注入
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate (@Qualifier("primaryDataSource")  DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="secondaryJdbcTemplate")
    public JdbcTemplate  secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 创建事务管理器1
    @Bean(name = "txManager1")
    public PlatformTransactionManager txManager1(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 创建事务管理器2
    @Bean(name = "txManager2")
    public PlatformTransactionManager txManager2(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //默认使用哪一个事务管理器
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return defaultTxManager;
    }
}



