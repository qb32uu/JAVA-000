package com.training.week03.q1.inbound;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.week03.q1.filter.HttpRequestFilter;
import com.training.week03.q1.router.HttpEndpointRouter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpInboundServer {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);

    private int port;
    private HttpEndpointRouter router;
    private List<HttpRequestFilter> requestFilters;

    public HttpInboundServer(int port, HttpEndpointRouter router, List<HttpRequestFilter> requestFilters) {
        this.requestFilters = requestFilters;
        this.port = port;
        this.router = router;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128) // 未完成的链接数
                    .option(ChannelOption.TCP_NODELAY, true) // true时不使用Nagle算法减少数据包，增加网络使用率但可提高响应时间
                    .option(ChannelOption.SO_KEEPALIVE, true) // tcp keppalive，可说明后台端口是通的，但不确定应用是否能正常工作
                    .option(ChannelOption.SO_REUSEADDR, true) // 重用地址
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024) // 缓冲区
                    .option(EpollChannelOption.SO_REUSEPORT, true)// 重用端口
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 同步到下级worker中
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);// 内存池

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInboundInitializer(router, requestFilters));

            Channel ch = b.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
