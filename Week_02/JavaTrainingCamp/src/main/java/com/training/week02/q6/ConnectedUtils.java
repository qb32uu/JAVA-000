package com.training.week02.q6;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 连接工具
 * 
 * @author Dillion
 *
 */
public class ConnectedUtils {
    private static Logger logger = LogManager.getLogger(ConnectedUtils.class);

    public static final HttpClient HTTP_CLIENT = HttpClients.createDefault();
    public static final Map<String, String> EMPTY_MAP = new HashMap<>();

    public static PoolingHttpClientConnectionManager cm = null;

    /**
     * 基础返回值处理
     * 
     * @param requestUrl
     * @param statusCode
     * @param entity
     * @return
     */
    public static final String baseResponse(String requestUrl, int statusCode, String entity) {
        if (statusCode != 200) {
            logger.info(requestUrl + " 状态码：" + statusCode + "，返回内容：" + entity);
        }
        return entity;
    }

    /**
     * get请求
     * 
     * @param requestUrl 请求地址，不含参数
     * @param dataMap    参数
     * @return
     */
    public static final String requestGet(String requestUrl, Map<String, String> dataMap) {
        if (StringUtils.isBlank(requestUrl)) {
            // 无效请求
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(requestUrl);
        if (requestUrl.indexOf("?") == -1) {
            builder.append("?");
        }

        // 添加参数
        for (Entry<String, String> entry : dataMap.entrySet()) {
            builder.append("&");
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());

        }

        try {
            HttpResponse response = HTTP_CLIENT.execute(new HttpGet(builder.toString()));
            int statusCode = response.getStatusLine().getStatusCode();
            String entity = EntityUtils.toString(response.getEntity());
            logger.debug(builder.toString() + " | 状态码：" + statusCode + " | 返回内容：" + entity);

            return baseResponse(requestUrl, statusCode, entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 无参get请求
     * 
     * @param requestUrl 请求地址，不含参数
     * @return
     */
    public static final String requestGet(String requestUrl) {
        return requestGet(requestUrl, EMPTY_MAP);
    }

    /**
     * get请求，调用 json API
     * 
     * @param requestUrl
     * @param dataMap
     * @return
     */
    public static final JsonObject requestGet2Json(String requestUrl, Map<String, String> dataMap) {
        String response = requestGet(requestUrl, dataMap);
        if (StringUtils.isBlank(response)) {
            return null;
        }

        return new JsonParser().parse(response).getAsJsonObject();
    }

    /**
     * 无参get请求，调用 json API
     * 
     * @param requestUrl 请求地址，不含参数
     * @return
     */
    public static final JsonObject requestGet2Json(String requestUrl) {
        return new JsonParser().parse(requestGet(requestUrl)).getAsJsonObject();
    }

    /**
     * post请求
     * 
     * @param requestUrl 请求地址，不含参数
     * @param dataMap    参数
     * @return
     */
    public static final String requestPost(String requestUrl, Map<String, String> dataMap) {
        HttpPost post = new HttpPost(requestUrl);

        // 添加其它请求参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        for (Entry<String, String> entry : dataMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            HttpResponse response = HTTP_CLIENT.execute(post);
            return baseResponse(requestUrl, response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
