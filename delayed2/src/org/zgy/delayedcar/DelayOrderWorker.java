package org.zgy.delayedcar;

public class DelayOrderWorker implements Runnable {
    String body;
    public DelayOrderWorker(String body) {
        this.body = body;
    }
    @Override
    public void run() {
        //相关业务逻辑处理
        System.out.println(Thread.currentThread().getName()+" do something ……");
    }
}
