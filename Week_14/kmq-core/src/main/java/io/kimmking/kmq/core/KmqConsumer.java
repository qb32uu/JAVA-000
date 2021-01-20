package io.kimmking.kmq.core;

import io.kimmking.kmq.core.consumer.MyConsumerMessage;

public class KmqConsumer<T> {

    private final KmqBroker broker;

    private Kmq kmq;

    public KmqConsumer(KmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.kmq = this.broker.findKmq(topic);
        if (null == kmq)
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public MyConsumerMessage<T> poll(long timeout) {
        return kmq.poll(timeout);
    }

    public boolean consume(int id) {
        return kmq.consume(id);
    }
}
