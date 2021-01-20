package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.kimmking.kmq.core.consumer.MyArrayConsumerQueue;
import io.kimmking.kmq.core.consumer.MyConsumerMessage;

public final class Kmq<T> {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new MyArrayConsumerQueue<T>(capacity);
    }

    private String topic;

    private int capacity;

    private MyArrayConsumerQueue<T> queue;

    public boolean send(T t) {
        return queue.offer(t);
    }

    public MyConsumerMessage<T> poll() {
        return queue.poll();
    }

    public boolean consume(int id) {
        return queue.consume(id);
    }

    @SneakyThrows
    public MyConsumerMessage<T> poll(long timeout) {
        return queue.poll();
//        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

}
