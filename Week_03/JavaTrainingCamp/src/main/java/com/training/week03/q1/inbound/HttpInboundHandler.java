package com.training.week03.q1.inbound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.week03.q1.filter.HttpRequestFilter;
import com.training.week03.q1.outbound.httpclient4.HttpOutboundHandler;
import com.training.week03.q1.router.HttpEndpointRouter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
//    private final String proxyServer;
//    private HttpOutboundHandler handler;
    private HttpEndpointRouter router;
    private List<HttpRequestFilter> requestFilters;
    private Map<String, HttpOutboundHandler> handlerMap = new HashMap<>(); // handler映射

    public HttpInboundHandler(HttpEndpointRouter router, List<HttpRequestFilter> requestFilters) {
        this.requestFilters = requestFilters;
        this.router = router;

        for (String proxyServer : router.getEndpoints()) {
            handlerMap.put(proxyServer, new HttpOutboundHandler(proxyServer));
        }
//        this.proxyServer = proxyServer;
//        handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
//            String uri = fullRequest.uri();
//            //logger.info("接收到的请求url为{}", uri);
//            if (uri.contains("/test")) {
//                handlerTest(fullRequest, ctx);
//            }

            // 调用handler进行业务处理
//            handler.handle(fullRequest, ctx);
            String proxyDealByRouter = this.router.route(fullRequest); // 选取一个服务
            HttpOutboundHandler handler = handlerMap.get(proxyDealByRouter);

            // 添加请求过滤器
            for (HttpRequestFilter rf:requestFilters) {
                if(!rf.filter(fullRequest,ctx)) {
                    return ;
                }
            }
            handler.handle(fullRequest, ctx);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

//    固定返回
//    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
//        FullHttpResponse response = null;
//        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());
//
//        } catch (Exception e) {
//            logger.error("处理测试接口出错", e);
//            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
//        } finally {
//            if (fullRequest != null) {
//                if (!HttpUtil.isKeepAlive(fullRequest)) {
//                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
//                } else {
//                    response.headers().set(CONNECTION, KEEP_ALIVE);
//                    ctx.write(response);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }

}
