package org.rcfeng.demo.utils.commons;

import com.ueb.commons.utils.SpendUtils;

public class Demo_$_SpendUtils {

	public static void main(String[] args) throws InterruptedException {
		SpendUtils.begin();
		
		Thread.sleep(1000);
		
		System.out.println(SpendUtils.resume());
		
		
		SpendUtils.end();
		
	}
}
