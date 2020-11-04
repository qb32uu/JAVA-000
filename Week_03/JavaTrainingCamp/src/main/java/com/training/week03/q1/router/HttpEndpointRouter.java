package com.training.week03.q1.router;

import java.util.List;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpEndpointRouter {

    String route(FullHttpRequest fullRequest);

    List<String> getEndpoints();
}
