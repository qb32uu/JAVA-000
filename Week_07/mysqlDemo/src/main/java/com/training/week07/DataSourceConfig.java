package com.training.week07;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    @Primary
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource primaryDataSource() {
        System.out.println("主数据库连接池创建中...");
        return DataSourceBuilder.create().build();
    }
}
