学习笔记

# 写订单表

对应项目为mysqlDemo，表结构为：mysqlDemo\src\main\resources\表结构.sql，基础数据由mysqlDemo\src\test\java\com\training\week07\CreateDataTest.java构造。

实验使用jdbc与hikari连接池两种连接，测试了模拟下单业务处理（根据skuId等查数据处理后写入）与直接写两种情况，同时测试了1~8个线程插入1000条订单数据的情况。

实验结论为：数据库、应用服务在同一台机器的情况下增加线程数，对性能基本没影响。hikari的耗时是jdbc 60%，无业务逻辑（直接写数据库）是有业务逻辑耗时的2.5%

|               | hikari  | jdbc    | hikari/jdbc |
| ------------- | ------- | ------- | ----------- |
| 无业务逻辑    | 756ms   | 1213ms  | 62.32%      |
| 有业务逻辑    | 29869ms | 49702ms | 60.10%      |
| 无逻辑/有逻辑 | 2.53%   | 2.44%   |             |