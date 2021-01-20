package io.kimmking.kmq.core.consumer;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于数组的确认消费队列，无阻塞不能读写时快速失败。
 * 
 * 通过数据与读写下标模拟队列，下标可以循环移动，但写队列不能超过读队列
 * 通过【消费状态数组】标记消息消费状态，消费确认后尝试（只能移动到己消费的位置）移动消费下标。
 * 
 * 
 * @author Billion
 *
 * @param <T>
 */
public class MyArrayConsumerQueue<T> {
    private ReentrantLock takeLock = new ReentrantLock();

    /**
     * 消费状态数组
     */
    private boolean[] consumerStates;
    /**
     * 数据数组
     */
    private MyConsumerMessage<T>[] items;
    /**
     * 队列当前未读取数据量
     */
    private int pollCount;
    /**
     * 队列当前未消费数据量
     */
    private int consumeCount;

    /**
     * 待写下标
     */
    private int offerIndex = 0;
    /**
     * 待读下标
     */
    private int pollIndex = 0;
    /**
     * 己确认消费下标
     */
    private int consumeIndex = 0;

    public MyArrayConsumerQueue(int size) {
        super();
        consumerStates = new boolean[size];
        items = new MyConsumerMessage[size];
    }

    /**
     * 写数据到队尾
     * 
     * @param t
     * @return
     */
    public boolean offer(T t) {
        takeLock.lock();
        try {

            // 当队列内容己无空间
            if (consumeCount == items.length) {
                // 写入失败
                return false;
            }

            items[offerIndex] = new MyConsumerMessage<>(t, offerIndex);
            consumerStates[offerIndex] = false;
            ++pollCount;// 未读+1
            ++consumeCount;// 未确认+1

            // 如果到数组尾，循环移回数据头
            if (++offerIndex == items.length) {
                offerIndex = 0;
            }

            return true;
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 读数据
     * 
     * @return
     */
    public MyConsumerMessage<T> poll() {
        takeLock.lock();
        try {
            // 当队列内容己无空间
            if (pollCount == 0) {
                return null;
            }
            MyConsumerMessage<T> msg = items[pollIndex];
            --pollCount;// 己读一条

            // 如果到数组尾，循环移回数据头
            if (++pollIndex == items.length) {
                pollIndex = 0;
            }

            return msg;
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 确认消费
     * 
     * @param index
     * @return
     */
    public boolean consume(int index) {
        takeLock.lock();
        try {

            if (index >= consumerStates.length) {
                return false;
            }

            consumerStates[index] = true;// 确认消费
            while (consumerStates[consumeIndex] && consumeCount != 0) {
                --consumeCount;// 确认一条
                // 如果到数组尾，循环移回数据头
                if (++consumeIndex == items.length) {
                    consumeIndex = 0;
                }
            }

            return true;

        } finally {
            takeLock.unlock();
        }
    }
}
