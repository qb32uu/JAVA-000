package com.training.week03.q1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.training.week03.q1.filter.LocalhostHttpRequestFilter;
import com.training.week03.q1.inbound.HttpInboundServer;
import com.training.week03.q1.router.HttpEndpointRouter;
import com.training.week03.q1.router.WeightedRoundHttpEndpointRouter;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//        String proxyPort = System.getProperty("proxyPort","8888");
        // http://localhost:8888/api/hello ==> gateway API
        // http://localhost:8088/api/hello ==> backend service
        
        String proxyPort = "8888";
        int port = Integer.parseInt(proxyPort);
        
        //轮询
//        List<String> proxyServers = Arrays.asList("http://localhost:8080", "http://localhost:8088");
//        HttpEndpointRouter roundRouter = new RoundHttpEndpointRouter(proxyServers);  
//        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
//        HttpInboundServer server = new HttpInboundServer(port, roundRouter, Arrays.asList(new LocalhostHttpRequestFilter()));
//        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port
//                + " for server:" + proxyServers);

        
        // 加权轮询， endpointMap：url->权重
        Map<String, Integer> endpointMap = new HashMap<>();
        endpointMap.put("http://localhost:8080", 1);
        endpointMap.put("http://localhost:8088", 2);
        HttpEndpointRouter weightedRouter = new WeightedRoundHttpEndpointRouter(endpointMap);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, weightedRouter, Arrays.asList(new LocalhostHttpRequestFilter()));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port
                + " for server:" + endpointMap);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
