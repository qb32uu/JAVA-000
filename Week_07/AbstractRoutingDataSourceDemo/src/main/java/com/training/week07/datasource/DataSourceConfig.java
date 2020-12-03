package com.training.week07.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterSource() {
        System.out.println("主库连接池创建中...");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1Source() {
        System.out.println("从库1连接池创建中...");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2Source() {
        System.out.println("从库2连接池创建中...");
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @DependsOn({ "masterSource", "slave1Source", "slave2Source" }) // 指定依赖对象，防止循环引用
    public DynamicDataSource dataSource(DataSource masterSource, DataSource slave1Source, DataSource slave2Source) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DataSourceEnum.MASTER, masterSource);
        targetDataSources.put(DataSourceEnum.SLAVE1, slave1Source);
        targetDataSources.put(DataSourceEnum.SLAVE2, slave2Source);
        return new DynamicDataSource(masterSource, targetDataSources);
    }

}
