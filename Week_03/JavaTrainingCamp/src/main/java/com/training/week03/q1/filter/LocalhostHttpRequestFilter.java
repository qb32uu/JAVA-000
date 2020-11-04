package com.training.week03.q1.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 只允许localhost的请求
 * 
 * @author Billion
 *
 */
public class LocalhostHttpRequestFilter implements HttpRequestFilter {
    private static Logger logger = LoggerFactory.getLogger(LocalhostHttpRequestFilter.class);

    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        // 只允许localhost的请求
        return fullRequest.headers().get("Host").startsWith("localhost");
    }
}
