学习笔记

# 写100W条订单表

对应项目为mysqlDemo，表结构为：mysqlDemo\src\main\resources\表结构.sql，基础数据由mysqlDemo\src\test\java\com\training\week07\CreateDataTest.java构造。

实验使用jdbc与hikari连接池两种连接，测试了模拟下单业务处理（根据skuId等查数据处理后写入）与直接写两种情况，同时测试了1~8个线程插入1000条订单数据的情况。

实验结论为：数据库、应用服务在同一台机器的情况下增加线程数，对性能基本没影响。hikari的耗时是jdbc 60%，无业务逻辑（直接写数据库）是有业务逻辑耗时的2.5%

|               | hikari  | jdbc    | hikari/jdbc |
| ------------- | ------- | ------- | ----------- |
| 无业务逻辑    | 756ms   | 1213ms  | 62.32%      |
| 有业务逻辑    | 29869ms | 49702ms | 60.10%      |
| 无逻辑/有逻辑 | 2.53%   | 2.44%   |             |



# 读写分离-动态切换数据源版本1.0

读写分离关键类在com.training.week07.datasource包中
在DataSourceConfig中创建多个数据源，并生成继承AbstractRoutingDataSource(DynamicDataSource)的Bean dataSource。
DynamicDataSource 使用ThreadLocal在设置本次连接数据源标识，实现当次处理固定数据源。
ReadOnlyAspect切面实现注解动态设置DynamicDataSource的连接数据源标识

本次测试在三个数据源的rw（表结构在resources\表结构.sql）中录入不同数量的记录，配置了1主2从三个数据源。
测试入口为test的com.training.week07.rw.RwServiceTest，getAll方法走主库getAllBySlave走从库。以下是测试结果
getAll第1次读数据量：101
getAll第2次读数据量：101
getAllBySlave第1次读，数据量：96
getAllBySlave第2次读，数据量：81
getAllBySlave第3次读，数据量：96
getAllBySlave第4次读，数据量：81
getAll第3次读数据量：101
getAllBySlave第5次读，数据量：96
getAllBySlave第6次读，数据量：81