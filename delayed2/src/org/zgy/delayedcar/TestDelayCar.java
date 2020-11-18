package org.zgy.delayedcar;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDelayCar {
    public static void main(String[] args) {
        DelayOrderWorker work1 = new DelayOrderWorker("AAA");// 任务1
        DelayOrderWorker work2 = new DelayOrderWorker("BBB");// 任务2
        DelayOrderWorker work3 = new DelayOrderWorker("CCC");// 任务3
        // 延迟队列管理类，将任务转化消息体并将消息体放入延迟对列中等待执行
        DelayOrderQueueManager manager = DelayOrderQueueManager.getInstance();
        manager.put(work1, 3000, TimeUnit.MILLISECONDS);
        manager.put(work2, 6000, TimeUnit.MILLISECONDS);
        manager.put(work3, 9000, TimeUnit.MILLISECONDS);

//        DelayOrderWorker work = new DelayOrderWorker();
//        List<Integer> list = Stream.of(10000, 20000, 30000).collect(Collectors.toList());
//        // 如果离线，将任务添加到延迟队列
//        manager.put(work, 10000, TimeUnit.MILLISECONDS);
    }
}
