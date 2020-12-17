package io.kimmking.rpcfx.client;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RpcfxByteBuddy {
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> serviceClass, final String url)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        return (T) new ByteBuddy().subclass(Object.class).implement(serviceClass).method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new RpcfxInvocationHandler(serviceClass, url)))
//                .intercept(MethodDelegation.to(new AgentInterceptor(url, serviceClass)))
                .make().load(RpcfxByteBuddy.class.getClassLoader()).getLoaded().getDeclaredConstructor().newInstance();

    }

//    public static class AgentInterceptor {
//        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
//        private String url;
//        private Class<?> serviceClass;
//
//        public AgentInterceptor(String url, Class<?> serviceClass) {
//            this.url = url;
//            this.serviceClass = serviceClass;
//        }
//
//        /**
//         * @param proxy  代理对象
//         * @param method 代理方法
//         * @param params 方法参数
//         */
//        public Object interceptor(@Origin String method, @AllArguments Object[] params) throws IOException {
//            RpcfxRequest request = new RpcfxRequest();
//            request.setServiceClass(this.serviceClass);
//            request.setMethod(method);
//            request.setParams(params);
//            RpcfxResponse response = post(request, url);
//            return JSON.parse(response.getResult().toString());
//        }
//
//        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
//            String reqJson = JSON.toJSONString(req);
//            OkHttpClient client = new OkHttpClient();
//            final Request request = new Request.Builder().url(url).post(RequestBody.create(JSONTYPE, reqJson)).build();
//            String respJson = client.newCall(request).execute().body().string();
//            return JSON.parseObject(respJson, RpcfxResponse.class);
//        }
//    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
        private static final PoolingHttpClientConnectionManager CM = new PoolingHttpClientConnectionManager();
        static {
            CM.setMaxTotal(200);
            CM.setDefaultMaxPerRoute(20);
        } // 构建请求配置信息
        private static final RequestConfig RC = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
                .setConnectionRequestTimeout(500) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(10 * 1000) // 数据传输的最长时间10s
//                .setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
                .build();

        private final Class<?> serviceClass;
        private final String url;

        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass);
            request.setMethod(method.getName());
            request.setParams(params);

            RpcfxResponse response = doPost(request, url);
            return JSON.parse(response.getResult().toString());
        }

        private CloseableHttpClient getHttpClient() {
            return HttpClients.custom().setConnectionManager(CM).build();
        }

        private RpcfxResponse doPost(RpcfxRequest req, String url) throws Exception {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: " + reqJson);
            HttpPost post = new HttpPost(url);

            // 设置请求配置信息
            post.setConfig(RC);

            CloseableHttpResponse response = null;

            ByteArrayEntity entity = null;
            try {
                entity = new ByteArrayEntity(reqJson.getBytes("UTF-8"));
                entity.setContentType("application/json");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException("向服务器承保接口发起http请求,封装请求body时出现异常", e);
            }
            post.setEntity(entity);
            try {
                // 执行请求
                response = getHttpClient().execute(post);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
                    return JSON.parseObject(respJson, RpcfxResponse.class);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            return null;
        }
    }

}
