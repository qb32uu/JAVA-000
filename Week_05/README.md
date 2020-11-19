### 写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github。

代码在：JavaTrainingCamp项目，测试入口为com.training.week05.bean.SpringAddBeanDemo.java



### 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

自动配置为spring-starter-demo项目，装配类为：com.qb32uu.spring.auto.starter.AutoConfiguration.java

测试DEMO为springBootTest项目，启动入口com.qb32uu.bean.SpringBootTestApplication.java


### 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
代码在mysqlDemo项目
测试数据库名java_camp，表结构在src\main\resources\表结构.sql

jdbc 在com.example.demo.base.JdbcDemo.java 测试为main方法，数据库配置在Connection com.example.demo.base.MysqlUtils.getConnection()
PreparedStatement 在com.example.demo.base.PrepareStatementDemo.java 测试为main方法，数据库配置在com.example.demo.base.MysqlUtils.getConnection()
Hikari 在com.example.demo.web.HikariDemo 测试以spring boot方式启动项目（com.example.demo.DemoApplication），后访问http://localhost:18080/test，数据库配置在src\main\resources\application.ym