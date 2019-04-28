package org.rcfeng.demo.thread;

/**
 * 使用join让当前线程等待子线程执行完成
 * @author hesp
 *
 */
public class Demo_2_Join {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("3");
            }
        });
        t.start();
//        t.join();
        t1.start();
    }
}
