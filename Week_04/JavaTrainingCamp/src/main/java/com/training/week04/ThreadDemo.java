package com.training.week04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.training.week04.vo.BizDataVo;

/**
 * 直接在主线程启动新线程进行计算
 * 
 * @author Billion
 *
 */
public class ThreadDemo {

    /**
     * 通过额外空间记录状态实现等待
     * 
     * @param data
     * @return
     */
    public int sleep(final BizDataVo data) {
        new Thread(() -> data.compute()).start();

        // 等侍处理，设置最长等待时间 20次SLEEP_TIME
        try {
            for (int i = 0; i < 20; i++) {
                TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
                if (data.isAllFinished()) {
                    break;
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data.getResult();
    }

    /**
     * 通过join实现等待
     * 
     * @param data
     * @return
     */
    public int join(final BizDataVo data) {
        Thread thread = new Thread(() -> data.compute());
        thread.start();

        // 实现等待处理完成
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data.getResult();
    }

    /**
     * 通过synchronized锁实现等待
     * 
     * @param data
     * @return
     */
    public int syn(final BizDataVo data) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                data.compute();
            }
        }).start();

        // 等侍子线程启动
        try {
            TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            return data.getResult();
        }
    }

    /**
     * 通过自旋锁实现等待
     * 
     * @param data
     * @return
     */
    public int cas(final BizDataVo data) {
        AtomicReference<Integer> cas = new AtomicReference<>();
        new Thread(() -> {
            cas.compareAndSet(null, 1);
            data.compute();
            cas.compareAndSet(1, null);
        }).start();

        // 等侍子线程启动
        try {
            TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 写锁等读锁实现等待
        while (!cas.compareAndSet(null, 2)) {
        }

        return data.getResult();
    }

    /**
     * 通过锁实现等待
     * 
     * @param data
     * @return
     */
    public int lock(final BizDataVo data) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            data.compute();
            lock.unlock();
        }).start();

        // 等侍子线程启动
        try {
            TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 写锁等读锁实现等待

        lock.lock();
        return data.getResult();
    }

    /**
     * 通过readWriteLock实现等待
     * 
     * @param data
     * @return
     */
    public int readWriteLock(final BizDataVo data) {
        ReadWriteLock rwl = new ReentrantReadWriteLock();
        new Thread(() -> {
            rwl.readLock().lock();
            data.compute();
            rwl.readLock().unlock();
        }).start();

        // 等侍子线程启动
        try {
            TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 写锁等读锁实现等待
        rwl.writeLock().lock();
        rwl.writeLock().unlock();

        return data.getResult();
    }

    /**
     * 通过countDownLatch实现等待
     * 
     * @param data
     * @return
     */
    public int countDownLatch(final BizDataVo data) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            data.compute();
            latch.countDown();
        }).start();

        // 等侍子线程启动
        try {
            TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // countDownLatch等待
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        return data.getResult();
    }

    /**
     * 主线程wait实现等待
     * 
     * @param data
     * @return
     */
    public int wait(final BizDataVo data) {
        Object msg = new Object();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME); // 保证主线程己经wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (msg) {
                data.compute();
                msg.notify();
            }
        }).start();
        synchronized (msg) {
            try {
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return data.getResult();
    }

    /**
     * 主线程Condition.await实现等待
     * 
     * @param data
     * @return
     */
    public int condition(final BizDataVo data) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME); // 保证主线程己经wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            data.compute();
            condition.signal();
            lock.unlock();
        }).start();

        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return data.getResult();

    }

    /**
     * 主线程子线程相互等共同完成
     * 
     * @param data
     * @return
     */
    public int cyclicBarrier(final BizDataVo data) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(BizDataVo.SLEEP_TIME); // 保证主线程己经wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.compute();
            try {
                cyclicBarrier.await(); // 相互等
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            cyclicBarrier.await(); // 相互等
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return data.getResult();

    }
}
