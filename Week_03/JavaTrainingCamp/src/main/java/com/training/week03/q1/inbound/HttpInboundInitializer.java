package com.training.week03.q1.inbound;

import java.util.List;

import com.training.week03.q1.filter.HttpRequestFilter;
import com.training.week03.q1.router.HttpEndpointRouter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

//	private String proxyServer;
    private HttpEndpointRouter router;
    private List<HttpRequestFilter> requestFilters;

    public HttpInboundInitializer(HttpEndpointRouter router, List<HttpRequestFilter> requestFilters) {
        this.requestFilters = requestFilters;
//        this.proxyServer = proxyServer;
        this.router = router;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
//      if (sslCtx != null) {
//          p.addLast(sslCtx.newHandler(ch.alloc()));
//      }
        p.addLast(new HttpServerCodec());
        // p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundHandler(this.router, requestFilters));
    }

//	@Override
//	public void initChannel(SocketChannel ch) {
//		ChannelPipeline p = ch.pipeline();
////		if (sslCtx != null) {
////			p.addLast(sslCtx.newHandler(ch.alloc()));
////		}
//		p.addLast(new HttpServerCodec());
//		//p.addLast(new HttpServerExpectContinueHandler());
//		p.addLast(new HttpObjectAggregator(1024 * 1024));
//		p.addLast(new HttpInboundHandler(this.proxyServer));
//	}
}
