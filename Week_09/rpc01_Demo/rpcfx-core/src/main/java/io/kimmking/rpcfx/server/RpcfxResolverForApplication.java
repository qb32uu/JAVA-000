package io.kimmking.rpcfx.server;

import org.springframework.context.ApplicationContext;

import io.kimmking.rpcfx.api.RpcfxResolver;

public class RpcfxResolverForApplication implements RpcfxResolver {

    private ApplicationContext applicationContext;

    public RpcfxResolverForApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T resolve(Class<T> klass) {
        // 通过class指定，bean不用显式指定className
        return this.applicationContext.getBean(klass);
    }

}
