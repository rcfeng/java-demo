package org.rcfeng.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程原子增长
 * @author hesp
 *
 */
public class Demo_1_Atomic {

    int i = 0 ;
    volatile int j = 0 ;
    AtomicInteger k = new AtomicInteger() ;
    
    public void increment(){
        i++ ;
        j++ ;
        k.getAndIncrement();
    }
    
    public void run (){
        for (int i = 0; i < 100; i++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j++) {
                    increment() ;
                }
            }).start(); ;
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        
        System.out.println("i = " + this.i);
        System.out.println("j = " + this.j);
        System.out.println("k = " + this.k);
    }
    
    public static void main(String[] args) {
        
        new Demo_1_Atomic().run() ;
    }
}
