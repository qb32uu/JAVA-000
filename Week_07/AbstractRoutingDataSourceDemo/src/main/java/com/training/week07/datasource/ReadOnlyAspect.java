package com.training.week07.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadOnlyAspect implements Ordered {
    private static int READ_NO = 0;

    @Pointcut("execution(* com.training.week07.rw.I*Service.*(..))")
    public void performance() {
    }

    // 环绕通知
    @Around(value = "performance()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        ReadOnly readOnly = method.getAnnotation(ReadOnly.class);
        if (readOnly == null) {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER);
        } else {
            // 没考虑线程安全，读库负载跟sql关系很大，轮询不需要太精确
            DynamicDataSource.setDataSource(++READ_NO % 2 == 0 ? DataSourceEnum.SLAVE1 : DataSourceEnum.SLAVE2);
        }

        try {
            return point.proceed();
        } finally {
            // 当前线程处理完释放数据源指向
            DynamicDataSource.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
