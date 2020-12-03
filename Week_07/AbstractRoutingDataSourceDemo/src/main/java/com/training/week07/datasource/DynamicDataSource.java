package com.training.week07.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 使用线程局部变量，实现在determineCurrentLookupKey指定数据源
     */
    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 数据源的信息
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       目标数据源
     */
    public DynamicDataSource(DataSource dataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置本次线程数据源标识
     * @param dataSource
     */
    public static void setDataSource(DataSourceEnum dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 消空本次线程数据源标识
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
