package org.rcfeng.demo.jvm;

import java.util.ArrayList;
import java.util.List;

public class Demo_1_GC_Heap_PSYoungGen {

	/**
	 * -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -Xloggc:e:/Demo_1_GC_Heap_PSYoungGen_gc.log 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("===========================Start===========================");
		
		
		List<byte[]> list = new ArrayList<>() ;
		
		for (int i = 0; i < 100; i++) {
			list.add(new byte[1024_000]) ;	//每次产生1MB
			Thread.sleep(100);				//休眠100ms
		}
		
		
		
		System.out.println("===========================End===========================");
		
	}
}
