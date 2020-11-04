package com.training.week03.q1.router;

import java.util.List;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 轮询算法
 * 
 * @author Billion
 *
 */
public class RoundHttpEndpointRouter implements HttpEndpointRouter {
    private int no = 0;
    private int size;

    private List<String> endpoints;

    public RoundHttpEndpointRouter(List<String> endpoints) {
        super();
        this.endpoints = endpoints;
        this.size = endpoints.size();
    }

    @Override
    public String route(FullHttpRequest fullRequest) {
        return this.endpoints.get(++this.no % this.size);
    }

    @Override
    public List<String> getEndpoints() {
        return endpoints;
    }
}
