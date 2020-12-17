3、（必做）改造自定义RPC的程序，提交到github：

对应项目rpcfx-core

1）尝试将服务端写死查找接口实现类变成泛型和反射

对应io.kimmking.rpcfx.server.RpcfxInvoker

2）尝试将客户端动态代理改成AOP，添加异常处理

对应io.kimmking.rpcfx.client.RpcfxByteBuddy

3）尝试使用Netty+HTTP作为client端传输方式

对应io.kimmking.rpcfx.client.RpcfxByteBuddy



