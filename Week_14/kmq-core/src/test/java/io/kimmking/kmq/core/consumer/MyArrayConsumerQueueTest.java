package io.kimmking.kmq.core.consumer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


class MyArrayConsumerQueueTest {

    @Test
    void test() {
        MyArrayConsumerQueue<Integer>  queue ;
        MyConsumerMessage<Integer> msg ;
        queue = new MyArrayConsumerQueue<>(5);
        
        Assert.assertEquals(true,queue.offer(0));
        Assert.assertEquals(true,queue.offer(1));
        Assert.assertEquals(true,queue.offer(2));
        Assert.assertEquals(true,queue.offer(3));
        Assert.assertEquals(true,queue.offer(4));
        
//写满不能写
        Assert.assertEquals(false,queue.offer(5));
       
        msg =    queue.poll();
        Assert.assertEquals(Integer.valueOf(0),msg.getMsg());
        
        //未确认不能写
        Assert.assertEquals(false,queue.offer(6));
        Assert.assertEquals(0,msg.getId());
        Assert.assertEquals(true,queue.consume(msg.getId()));
        //己确认可以写
        Assert.assertEquals(true,queue.offer(7));
        

        msg =    queue.poll();
        Assert.assertEquals(Integer.valueOf(1),msg.getMsg());
        Assert.assertEquals(1,msg.getId());
        
        msg =    queue.poll();
        Assert.assertEquals(Integer.valueOf(2),msg.getMsg());
        Assert.assertEquals(2,msg.getId());
        Assert.assertEquals(true,queue.consume(msg.getId()));
        //前面的消息未确认，确认指针不移，不能写
        Assert.assertEquals(false,queue.offer(8));
        
        Assert.assertEquals(true,queue.consume(1));
        //确认后会尝试用量移确认指针，可以写两个
        Assert.assertEquals(true,queue.offer(9));
        Assert.assertEquals(true,queue.offer(10));

        

    }

  
}
