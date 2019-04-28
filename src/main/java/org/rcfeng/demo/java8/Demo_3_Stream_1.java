package org.rcfeng.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.rcfeng.demo.vo.People;

/**
 * Java8 Stream 管道流处理
 * @author hesp
 *
 */
public class Demo_3_Stream_1 {

	/**
	 * filter()  						 	需return 返回值  		遍历返回Stream类型
	 * map()  		类似于Lists.transform 	需return 返回值  		遍历返回Stream类型
	 * peek()   							没有return返回值           	遍历返回Stream类型
	 * forEach() 							没有return返回值 		返回void					结束Stream流
	 */
	
	public static void main(String[] args) {
//		filter_peek_map_forEach();
		peek_forEach();
	}
	
	
	/**
	 * 执行顺序及各个方法的作用
	 */
	public static void filter_peek_map_forEach(){
		List<String> list = Arrays.asList("A","B","C","A1","D");
        list.stream()
	        .filter(s -> !s.startsWith("A"))		//Predicate 过滤以A开头的对象
	        .peek(s -> System.out.println(s))		//Consumer  遍历
	        .map((s) ->  s + "_map")				//Function  转换
	        .forEach(s -> System.out.println(s)) ;	//Consumer  遍历
	}
	
	/**
	 * peek和forEach
	 */
	public static void peek_forEach(){
		List<People> list = Arrays.asList(new People(),new People(),new People());
		
		list.forEach(p -> p.setAge(new Random().nextInt(10)+10));
		list.stream()
			.peek(p -> System.out.println(String.format(" First time - name:[%s] age:[%d] qq:[%d] \n", p.getName(), p.getAge(), p.getQq())))
			.peek(p -> p.setName(geneRandomString(new Random().nextInt(7)+3)))
			.peek(p -> System.out.println(String.format(" Second time - name:[%s] age:[%d] qq:[%d] \n", p.getName(), p.getAge(), p.getQq())))
			.peek(p -> p.setQq(new Random().nextInt(899999)+10000))
			.forEach(p -> System.out.println(String.format(" Third time - name:[%s] age:[%d] qq:[%d] \n====================================================", p.getName(), p.getAge(), p.getQq())));
		//以上代码与以下代码等价
//		for (People p : list) {
//			System.out.println(String.format(" First time - name:[%s] age:[%d] qq:[%d] \n", p.getName(), p.getAge(), p.getQq())) ;
//			p.setName(geneRandomString(new Random().nextInt(7)+3)) ;
//			System.out.println(String.format(" Second time - name:[%s] age:[%d] qq:[%d] \n", p.getName(), p.getAge(), p.getQq())) ;
//			p.setQq(new Random().nextInt(899999)+10000) ;
//			System.out.println(String.format(" Third time - name:[%s] age:[%d] qq:[%d] \n====================================================", p.getName(), p.getAge(), p.getQq())) ;
//		}
	}
	
	
	/**
	 * 随机生成字符串
	 * @param num 长度
	 * @return
	 */
	private static String geneRandomString(int num){
		StringBuilder sb = new StringBuilder(num);
		Random r = new Random();
		for (int i = 0; i < num; i++) {
			sb.append((char) ('a' + r.nextInt(26)));
		}
		return sb.toString() ;
	}
}
