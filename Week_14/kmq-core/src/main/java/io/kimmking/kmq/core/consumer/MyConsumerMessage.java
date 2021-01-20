package io.kimmking.kmq.core.consumer;

import lombok.Data;

@Data
public class MyConsumerMessage<T> {
    private T msg;
    /**
     * 消息id，确认消费时使用
     */
    private int id;

    public MyConsumerMessage(T msg, int id) {
        super();
        this.msg = msg;
        this.id = id;
    }
    
    
}
