package com.training.week07.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
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
    public DataSource dataSource(DataSource masterSource, DataSource slave1Source, DataSource slave2Source) {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        dataSourceMap.put("ds0", masterSource);
        dataSourceMap.put("ds1", slave1Source);
        dataSourceMap.put("ds2", slave2Source);

        // 配置读写分离规则
        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "ds0", Lists.newArrayList("ds1", "ds2"),
                "load_balancer"));
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
        loadBalancers.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations,
                loadBalancers);

        DataSource dataSource = null;
        try {
            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap,
                    Collections.singleton(ruleConfiguration), new Properties());
        } catch (SQLException e) {
            log.error("create datasource failed, error is {}", e);
        }

        log.info("datasource : {}", dataSource);
        return dataSource;
    }

}
