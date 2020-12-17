package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    public <T> T resolve(Class<T> klass);

}
