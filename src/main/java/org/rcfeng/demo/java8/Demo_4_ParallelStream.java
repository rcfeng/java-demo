package org.rcfeng.demo.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Java8 并发流
 * @author hesp
 *
 */
public class Demo_4_ParallelStream {
	
	/**
	 * int corePoolSize		线程池初始大小,按照该数值启动线程,当workQueue队列满了的情况下才会创建超出这个数量的线程,且不超过maximumPoolSize
	 * int maximumPoolSize	线程池最大线程数,
	 * long keepAliveTime
	 * TimeUnit unit
	 * BlockingQueue<Runnable> workQueue
	 * RejectedExecutionHandler handler
	 */
	private static ExecutorService exe = new ThreadPoolExecutor(1,20, 300,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) throws InterruptedException {
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 109; i++) {
			list.add(i) ;
		}
		
//		java6_for(list);
//		java8_forEach(list);
//		java8_parallelStream_forEach(list);
//		java8_parallelStream_forEach_sleep(list);		//java8并发流的线程数固定为cpu核数
//		threadPoolExecutor_sleep(list);					//线程池的线程数为自定义
	}
	
	/**
	 * Java6循环打印
	 * @param list
	 */
	public static void java6_for(List<Integer> list){
		System.out.println("##########java6_for##########");
		long begin = System.currentTimeMillis() ;
		for (Integer i : list) {
			println(i);
		}
		long end = System.currentTimeMillis() ;
		System.out.println("##########java6_for########## = "+(end-begin));
	}
	
	/**
	 * Java8 循环打印
	 * @param list
	 */
	public static void java8_forEach(List<Integer> list){
		System.out.println("##########java8_forEach##########");
		long begin = System.currentTimeMillis() ;
		list.forEach(i -> {
			println(i);
		});
		long end = System.currentTimeMillis() ;
		System.out.println("##########java8_forEach########## = "+(end-begin));
	}
	
	/**
	 * Java8 并发流循环打印
	 * @param list
	 */
	public static void java8_parallelStream_forEach(List<Integer> list){
		System.out.println("##########java8_parallelStream_forEach##########");
		long begin = System.currentTimeMillis() ;
		list.parallelStream().forEach(i -> {
			println(i);
		});
		long end = System.currentTimeMillis() ;
		System.out.println("##########java8_parallelStream_forEach########## = "+(end-begin));
	}
	
	/**
	 * Java8 并发流循环打印,睡眠监控线程数
	 * @param list
	 */
	public static void java8_parallelStream_forEach_sleep(List<Integer> list){
		System.out.println("##########java8_parallelStream_forEach_sleep##########");
		long begin = System.currentTimeMillis() ;
		list.parallelStream().forEach(i -> {
			println(i);
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		long end = System.currentTimeMillis() ;
		System.out.println("##########java8_parallelStream_forEach_sleep########## = "+(end-begin));
	}
	
	/**
	 * Java8 并发流循环打印,睡眠监控线程数
	 * @param list
	 */
	public static void threadPoolExecutor_sleep(List<Integer> list){
		System.out.println("##########threadPoolExecutor_sleep##########");
		long begin = System.currentTimeMillis() ;
		for (Integer i : list) {
			exe.submit(() -> {
				println(i);
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		long end = System.currentTimeMillis() ;
		System.out.println("##########threadPoolExecutor_sleep########## = "+(end-begin));
	}
	
	/**
	 * 打印休眠
	 * @param i
	 */
	public static void println(Integer i) {
		try {
			System.out.println(i);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
