package org.rcfeng.demo.jvm;

public class Demo_1_OOM_Heap {

	/**
	 * -Xmx20m -Xms20m -Xmn8m
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Start");
		byte[] b = new byte[12096_000] ;	//12MB 新生代放不下,自动转存到老年代
		System.out.println("End  ---  " + b.length);
		
		
	}
}
