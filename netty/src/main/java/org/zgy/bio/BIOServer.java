package org.zgy.bio;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 线程池机制
        ExecutorService exe = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            System.out.println("main-线程 -> id:" + Thread.currentThread().getId() + " , name:" + Thread.currentThread().getName());
            System.out.println("等待连接……");
            final Socket accept = serverSocket.accept(); // 这里会阻塞
            System.out.println("连接到一个客户端");
            exe.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    // 和客户端通讯
    public static void handler(Socket socket) {
        try {
            System.out.println("run-线程 -> id:" + Thread.currentThread().getId() + " , name:" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("read……");
                int read = inputStream.read(bytes); // 这里会阻塞
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class A1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        AtomicInteger num = new AtomicInteger(1);
        Thread t1 = new Thread() {
            @Override public void run() {
                while (num.intValue() < 100) {
                    if (num.intValue() % 2 == 1) {

                        System.out.println("奇数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override public void run() {
                while (num.intValue() <= 100) {
                    if (num.intValue() % 2 == 0) {
                        System.out.println("偶数线程:" + num.intValue());
                        num.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }
        };
        t1.start();
        t2.start();
        try {
            countDownLatch.await();

        }catch (Throwable e){
            System.out.println(e);
        }
    }
}

class A2{
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = n1.next;
        n2.setVal(2);
        System.out.println(swapPairs(n1));
    }
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // 存储传入的下一节点，例如传入节点1，保存的节点是节点2
        ListNode next = head.next;
        // 递归，将传入的下一节点指向下下个节点，
        // 例如head是节点1，next.next则为第三个节点
        head.next = swapPairs(next.next);
        // 例如传入的head为节点1，则next为节点2，
        // 将节点2的下一个节点指向节点1，进行交换
        next.next = head;
        // 返回节点2
        return next;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public static void main(String[] args) {
        System.out.println(new Date(1602486212000l));
        String s = "";
        String a = "";
        int b , c;

    }
}