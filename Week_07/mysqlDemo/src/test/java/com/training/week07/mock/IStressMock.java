package com.training.week07.mock;

public interface IStressMock {

    /**
     * 重置次数
     */
    public void clean();

    /**
     * 模拟购物
     * 
     * @param name 线程名
     * @return
     */
    public boolean shopping(String name);
}
