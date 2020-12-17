package io.kimmking.rpcfx.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        Class<?> serviceClass = request.getServiceClass();

        // 作业1：改成泛型和反射
        Object service = resolver.resolve(serviceClass);// this.applicationContext.getBean(serviceClass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod(), request.getParams());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {

            // 3.Xstream

            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    /**
     * 找出远程调用的方法，支持多态
     * 
     * @param klass
     * @param methodName
     * @param params
     * @return
     */
    private Method resolveMethodFromClass(Class<?> klass, String methodName, final Object[] params) {
        int paramsNo = params.length;

        // 找出方法名与参数数量相同的方法
        List<Method> methodList = Arrays.stream(klass.getMethods())
                .filter(m -> methodName.equals(m.getName()) && m.getParameterTypes().length == paramsNo)
                .collect(Collectors.toList());

        // 找出参数一致的方法（多态）
        for (Method method : methodList) {
            int i = 0;
            for (; i < params.length; i++) {
                if (!params[i].getClass().equals(Convert.wrap(method.getParameterTypes()[i]))) {
                    break;
                }
            }
            if (i == params.length) {
                return method;
            }
        }

        return null;
    }

}
