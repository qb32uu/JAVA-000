package com.training.week02.q6;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.training.week02.q6.ConnectedUtils;

public class ConnectedUtilsTest {
    @Test
    public void testRequestGet() {
        String response = null;

        response = ConnectedUtils.requestGet("http://www.baidu.com");
        Assert.assertNotNull("百度首页", response);

        Map<String, String> map = new HashMap<>();
        map.put("wd", "test");
        response = ConnectedUtils.requestGet("http://www.baidu.com/baidu", map);
        Assert.assertNotNull("百度查找test", response);
    }

    @Test
    public void testRequestGetToJson() {
        JsonObject response = null;

        response = ConnectedUtils.requestGet2Json("https://restapi.amap.com/v3/ip");
        System.out.println(response);
        Assert.assertNotNull("百度首页", response);

    }
}
