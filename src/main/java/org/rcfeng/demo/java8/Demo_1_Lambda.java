package org.rcfeng.demo.java8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Java8 Lambda表达式Demo
 * @author hesp
 *
 */
public class Demo_1_Lambda {
	
	public static void main(String[] args) {
		thread();
//		sort(); 
	}

	
	/**
	 * 线程demo
	 */
	public static void thread(){
		//###########Java6写法############
		System.out.println("\n\n###########Java6写法############");
		Runnable r1 = new Runnable() {
			public void run() {
				System.out.println("Hello world !");
			}
		};
		new Thread(r1).start(); 
		//###########Java6写法############
		
		
		
		
		
		//###########Lambda写法############
		System.out.println("\n\n###########Lambda写法############");
		Runnable r2 = () -> System.out.println("Hello Lambda 2 !");
		new Thread(r2).start(); 
		//###########Lambda写法############
		
		
		
		
		
		//###########Lambda写法2############
		System.out.println("\n\n###########Lambda写法2############");
		new Thread(() -> System.out.println("Hello Lambda 3 !")).start(); 
		//###########Lambda写法2############

		
		
		
		
		//###########Lambda写法多行逻辑代码############
		System.out.println("\n\n###########Lambda写法多行逻辑代码############");
		Runnable r4 = () -> {
			System.out.println("Hello Lambda 4 !") ;
			System.out.println(String.format("Hello %s !","Java8")) ;
		};
		new Thread(r4).start();  
		//###########Lambda写法多行逻辑代码############
	}
	
	public static void sort(){
		String[] players = {"Rafael Nadal", "Novak Djokovic",   
			    "Stanislas Wawrinka", "David Ferrer",  
			    "Roger Federer", "Andy Murray",  
			    "Tomas Berdych", "Juan Martin Del Potro",  
			    "Richard Gasquet", "John Isner"};  
			   
			// 1.1 使用匿名内部类根据 name 排序 players
		//###########Java6写法############
		Arrays.sort(players, new Comparator<String>() {  
		    @Override  
		    public int compare(String s1, String s2) {  
		        return (s1.compareTo(s2));  
		    }  
		});

		System.out.println("\n\n###########Java6 ASC############");
		for (String s : players) {
			System.out.println(s);
		}
		//###########Java6写法############
		
		
		
		System.out.println("\n\n###########Lambda DESC############");
		//###########Lambda写法############
		Arrays.sort(players, (s1, s2) -> s2.compareTo(s1));  
		for (String s : players) {
			System.out.println(s);
		}
		//###########Lambda写法############
	}
}
