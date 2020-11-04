package com.training.week03.q1.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 加权轮询算法
 * 
 * @author Billion
 *
 */
public class WeightedRoundHttpEndpointRouter implements HttpEndpointRouter {
    private int no = 0;
    private int size;

    private List<String> endpoints;

    public WeightedRoundHttpEndpointRouter(Map<String, Integer> endpointMap) {
        super();

        this.endpoints = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : endpointMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                endpoints.add(entry.getKey());
            }
        }

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
