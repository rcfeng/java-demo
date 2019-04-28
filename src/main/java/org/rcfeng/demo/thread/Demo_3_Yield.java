package org.rcfeng.demo.thread;

/**
 * 调用yield()方法的线程告诉虚拟机它乐意让其他线程占用自己的位置。这表明该线程没有在做一些紧急的事情。注意，这仅是一个暗示，并不能保证不会产生任何影响。
 * @author hesp
 * refrence http://www.importnew.com/14958.html
 */
public class Demo_3_Yield {

    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();
        producer.setPriority(Thread.MIN_PRIORITY); // Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); // Max Priority
        producer.start();
        consumer.start();
    }
}


class Producer extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am Producer : Produced Item " + i);
            Thread.yield();
        }
    }
}


class Consumer extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am Consumer : Consumed Item " + i);
            Thread.yield();
        }
    }
}
