package org.zgy.thread;

import java.util.ArrayList;
import java.util.List;

public class TestThread1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.clear();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("a ");
                System.out.println("list"+list);
            }
        });
        t1.start();
//        t1.join();

        list.clear();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                list.add("b ");
                System.out.println("list"+list);
            }
        });
        t2.start();
        //t2.join();
    }
}

class ThreadA implements Runnable {

    @Override
    public void run() {

    }
}
