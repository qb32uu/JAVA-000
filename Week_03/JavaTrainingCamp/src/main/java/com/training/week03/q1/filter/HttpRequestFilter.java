package com.training.week03.q1.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {
    /**
     * 
     * @param fullRequest
     * @param ctx
     * @return 处理结果
     */
    boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
    
}
