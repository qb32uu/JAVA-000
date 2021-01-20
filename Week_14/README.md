实现版本2

通过数据与读写下标模拟队列，下标可以循环移动，但写队列不能超过读队列

通过【消费状态数组】标记消息消费状态，消费确认后尝试（只能移动到己消费的位置）移动消费下标。

模拟队列类在io.kimmking.kmq.core.consumer.MyArrayConsumerQueue，返回含id的消息io.kimmking.kmq.core.consumer.MyConsumerMessage

测试代码在io.kimmking.kmq.core.consumer.MyArrayConsumerQueueTest