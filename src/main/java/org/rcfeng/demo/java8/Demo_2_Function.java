package org.rcfeng.demo.java8;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.rcfeng.demo.vo.People;

/**
 * Java8 funcation包接口Demo
 * @author hesp
 *
 */
public class Demo_2_Function {
	
	
	public static void main(String[] args) {
//		consumer();
//		supplier();
//		predicate();
		function();
	}
	

	/**
	 * 消费型接口
	 * Consumer<T> – 在T上执行一个操作，无返回结果 
	 */
	public static void consumer() {
		//###########传统写法  T = String类型############
		System.out.println("\n\n###########传统写法  T = String类型############");
		Consumer<String> consumerStr = new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(String.format("Hello %s !", s));
			}
		};
		consumerStr.accept("UEB");
		//###########传统写法  T = String类型############
		
		
		
		
		
		//###########传统写法  T = Integer类型############
		System.out.println("\n\n###########传统写法  T = Integer类型############");
		Consumer<Integer> consumerInt = new Consumer<Integer>() {
			@Override
			public void accept(Integer i) {
				System.out.println(String.format("Hello %d !", i));
			}
		};
		consumerInt.accept(168);
		//###########传统写法  T = Integer类型############
		
		
		
		
		
		List<String> list = new ArrayList<>() ;
		list.add("java") ;
		list.add("php") ;
		list.add("h5") ;
		//###########Lambda写法 预设Consumer方法############
		System.out.println("\n\n###########Lambda写法 预设Consumer方法############");
		Consumer<String> lambda = s -> {  
            System.out.println(String.format("Preset - Hello %s ,s.length() = %d !", s,s.length()));  
        };
        lambda.accept("Lambda !");
		list.forEach(lambda);
		//###########Lambda写法 预设Consumer方法############
		
		
		
		
		
		//###########Lambda写法 ############
		System.out.println("\n\n###########Lambda写法 ############");
		list.forEach(s -> System.out.println(String.format("ForEach - Hello %s ", s)));
		//###########Lambda写法 ############
	}
	
	
	
	/**
	 * 供给型接口
	 * Supplier<T> –无输入参数，返回T的实例 
	 */
	public static void supplier() {
		//###########传统写法  T = String类型############
		System.out.println("\n\n###########传统写法  T = String类型############");
		Supplier<String> supplier = new Supplier<String>() {
			@Override
			public String get() {
				System.out.println("Supplier - get()");
				return "Hello Supplier !";
			}
		};
		System.out.println(supplier.get());
		//###########传统写法  T = String类型############
		
		
		
		
		
		//###########Lambda写法 预设Supplier方法############
		System.out.println("\n\n###########Lambda写法 预设Supplier方法############");
		Supplier<Integer> lambda = () -> 5 * 6;
		System.out.println(lambda.get());
		//###########Lambda写法 预设Supplier方法############
	}
	
	
	
	/**
	 * 断言型接口
	 * Predicate<T> –输入参数为T的实例，返回boolean值 
	 */
	public static void predicate() {
		//###########传统写法  T = String类型############
		System.out.println("\n\n###########传统写法  T = String类型############");
		Predicate<String> predicateStr = new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return "UEB".equals(s);
			}
		};
		System.out.println(predicateStr.test("UEB"));
		//###########传统写法  T = String类型############
		
		
		
		
		
		//###########传统写法  T = Integer类型############
		System.out.println("\n\n###########传统写法  T = Integer类型############");
		Predicate<Integer> predicateInt = new Predicate<Integer>() {
			@Override
			public boolean test(Integer i) {
				return i < 100 ;
			}
		};
		System.out.println(predicateInt.test(110));
		//###########传统写法  T = Integer类型############
		
		
		
		
		
		List<String> list = new ArrayList<>() ;
		list.add("java") ;
		list.add("php") ;
		list.add("h5") ;
		//###########Lambda写法 预设Predicate方法############
		System.out.println("\n\n###########Lambda写法 预设Consumer方法############");
		Predicate<String> lambda = s -> s.length()>2;
		list.stream()
			.filter(lambda)
			.forEach(s -> System.out.println(s)) ;		//缓存在stream中,未改变list
		//###########Lambda写法 预设Predicate方法############
		
		
		
		
		
		//###########Lambda写法 ############
		System.out.println("\n\n###########Lambda写法 ############");
		list.stream()
			.filter(s -> s.startsWith("j"))
			.forEach(s -> System.out.println(s));
		//###########Lambda写法 ############
	}
	
	
	
	/**
	 * 功能型接口
	 * Function<T,R> –输入参数为T的实例，返回R的实例
	 */
	public static void function() {
		//###########传统写法  输入Double类型,返回BigDecimal类型(四舍五入保留两位小数)############
		System.out.println("\n\n###########传统写法  输入Double类型,返回BigDecimal类型(四舍五入保留两位小数)############");
		Function<Double,BigDecimal> function = new Function<Double,BigDecimal>() {
			@Override
			public BigDecimal apply(Double d) {
				BigDecimal decimal = new BigDecimal(d) ;
				return decimal.setScale(2,BigDecimal.ROUND_HALF_UP) ;
			}
		};
		System.out.println(function.apply(168.1456789));
		//###########传统写法  输入Double类型,返回BigDecimal类型(四舍五入保留两位小数)############
		
		
		
		
		
		List<People> list = new ArrayList<>() ;
		list.add(new People("zhangsan",8, 123456)) ;
		list.add(new People("lisi",9, 654321)) ;
		list.add(new People("hanmeimei",10, 147258369)) ;
		//###########Lambda写法 获取所有人的名字 ############
		System.out.println("\n\n###########Lambda写法 获取所有人的名字 ############");
		
		//预设Consumer方法
		Function<People,String> lambda = new Function<People,String>() {
			@Override
			public String apply(People p) {
				return p.getName() ;
			}
		};
		
		List<String> nameList = list.stream()		
//				.map(lambda)						//stream.map()相当于Guava的Lists.transform
//				.map(p -> p.getName())				//<R> Stream<R> map(Function<? super T, ? extends R> mapper);
				.map(People::getName)				//<R> Stream<R> map(Function<? super T, ? extends R> mapper);
				.collect(Collectors.toList()) ;		//结束stream返回List
		
		nameList.forEach(s -> System.out.println(s));
		//###########Lambda写法 获取所有人的名字 ############
	}
	
	
	
}