

# 第3课

## 分析题

分析.md

# 第4课

## 第2题

JavaTrainingCamp项目main方法为[com](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/main%5C/java=/optional=/true=/=/maven.pomderived=/true=/%3Ccom).[training](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/main%5C/java=/optional=/true=/=/maven.pomderived=/true=/%3Ccom.training).[week02](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/main%5C/java=/optional=/true=/=/maven.pomderived=/true=/%3Ccom.training.week02).[q6](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/main%5C/java=/optional=/true=/=/maven.pomderived=/true=/%3Ccom.training.week02.q6).Test.java，工具类的测试代码在：[com](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/test%5C/java=/optional=/true=/=/maven.pomderived=/true=/=/test=/true=/%3Ccom).[training](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/test%5C/java=/optional=/true=/=/maven.pomderived=/true=/=/test=/true=/%3Ccom.training).[week02](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/test%5C/java=/optional=/true=/=/maven.pomderived=/true=/=/test=/true=/%3Ccom.training.week02).[q6](http://eclipse-javadoc:%E2%98%82=JavaTrainingCamp/src%5C/test%5C/java=/optional=/true=/=/maven.pomderived=/true=/=/test=/true=/%3Ccom.training.week02.q6).ConnectedUtilsTest

核心代码：

```java
HttpResponse response = HttpClients.createDefault().execute(new HttpGet("http://localhost:8801"));
            return EntityUtils.toString(response.getEntity());
```
项目在核心代码的基础上支持指定URL、请求参数、get/post请求、json API的支持等
