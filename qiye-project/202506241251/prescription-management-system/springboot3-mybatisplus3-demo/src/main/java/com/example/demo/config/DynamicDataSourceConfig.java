package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DynamicDataSourceConfig {

    @Bean
    public DataSource dynamicDataSource(
            @Qualifier("primaryDataSource") DataSource primaryDataSource,
            @Qualifier("slaveDataSource") DataSource slaveDataSource) {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DbContextHolder.DbType.PRIMARY, primaryDataSource);
        targetDataSources.put(DbContextHolder.DbType.SLAVE, slaveDataSource);

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}