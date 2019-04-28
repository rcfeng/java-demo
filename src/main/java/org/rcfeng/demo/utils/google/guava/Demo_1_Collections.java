package org.rcfeng.demo.utils.google.guava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import ch.qos.logback.classic.net.SyslogAppender;

/**
 * Google Guava - Collections
 * @author hesp
 * 参考文档:http://www.open-open.com/lib/view/open1325143343733.html
 *
 */
public class Demo_1_Collections {

	public static void main(String[] args) {
		list();
	}
	
	private static void list() {
		
		//###########传统写法############
		List<String> list_o = new ArrayList<>() ;
		list_o.add("A") ;
		list_o.add("B") ;
		list_o.add("C") ;
		//###########传统写法############
		//###########Guava Collections写法############
		List<String> list_g = Lists.newArrayList("A","B","C") ;
		//###########Guava Collections写法############

		System.out.println("======= list_g forEach ========");
		list_g.forEach(s -> System.out.println(s));
		
		//###########Collections.reverse############
		
		System.out.println("=======Collections.reverse(list_g).forEach ========");
		Collections.reverse(list_g);		//改变原对象
		list_g.forEach(s -> System.out.println(s));
		
		
		
		System.out.println("=======Lists.reverse(list_g).forEach ========");
		Lists.reverse(list_g) ;		//不改变原对象,产生新对象
		list_g.forEach(s -> System.out.println(s));
		
		System.out.println("===============");
		List<String> list_g_reverse = Lists.reverse(list_g) ;
		list_g_reverse.forEach(s -> System.out.println(s));
		
		List<String> appendList = Lists.newArrayListWithCapacity(5) ;
		appendList.add("A") ;
		appendList.add("B") ;
		appendList.add("A") ;
		appendList.add("A") ;
		appendList.add("A") ;
		
		System.out.println("=======Lists.transform(list_g).forEach ========");
		Lists.transform(list_g_reverse, s -> s+="  apeend").forEach(s -> System.out.println(s));
//		Lists.partition(list, size);
//		
		
	}
}
