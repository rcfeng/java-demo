package org.rcfeng.demo.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;

import org.rcfeng.demo.vo.Person;

/**
 * Java8 stream组合使用
 * @author hesp
 *
 */
public class Demo_3_Stream_2 {

	
	/**
	 * 原文地址:http://blog.csdn.net/renfufei/article/details/24600507
	 * @param args
	 */
	
	public static void main(String[] args) {

		List<Person> javaProgrammers = new ArrayList<Person>() {
			{
				add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
				add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
				add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
				add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
				add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
				add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
				add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
				add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
				add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
				add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
			}
		};
		
		// 每个程序员的工资
		System.out.println("下面是每个程序员的工资");
		javaProgrammers.forEach(p -> showPerson(p));

		// 每人涨薪500
		System.out.println("下面是每个程序员涨薪500后的工资");
		javaProgrammers.forEach(p -> {
			p.setSalary(p.getSalary() + 500) ;
			showPerson(p) ;
		});

		// 工资超过2000的程序员
		System.out.println("下面是工资超过2000的程序员");
		javaProgrammers.stream().filter(p -> p.getSalary() > 2000)
								.forEach(p -> showPerson(p));


		// 定义 filters
		Predicate<Person> ageFilter = p -> p.getAge() > 24;								//筛选年龄大于24的程序员
		Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);					//筛选月薪大于1400的程序员
		Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));		//筛选女程序员

		System.out.println("下面是年龄大于 24岁且月薪在1400以上的女程序员:");
		/**
		 * SELECT 
		 * 		*
		 * FROM 
		 * 		Person
		 * WHERE
		 * 		age > 24
		 * AND
		 * 		salary > 1400
		 * AND
		 * 		gender = 'female'
		 */
		javaProgrammers.stream().filter(ageFilter)
								.filter(salaryFilter)
								.filter(genderFilter)
								.forEach(Demo_3_Stream_2::showPerson);
		
		
		System.out.println("下面是月薪最高的前3位程序员");
		/**
		 * SELECT 
		 * 		*
		 * FROM 
		 * 		Person
		 * ORDER BY 
		 * 		salary
		 * LIMIT
		 * 		3
		 */
		javaProgrammers.stream()
						.sorted((p1, p2) -> p2.getSalary() - p1.getSalary())		//从大到小排序
						.limit(3)													//取前3个对象
						.forEach(Demo_3_Stream_2::showPerson);
		
		
		
		System.out.println("下面是年龄最大程序员的名字");
		/**
		 * SELECT
				CONCAT(firstName,lastName)
			FROM
				Person
			WHERE
				age = (
					SELECT
						max(age)
					FROM
						Person
				)
		 */
		String personName = javaProgrammers.stream()
						.max((p1, p2) -> p1.getAge() - p2.getAge())										//返回单个对象
						.map(p -> String.format("name:[%s %s]",p.getFirstName(), p.getLastName()))		//转换对象(Optional<T>.map与 stream的map略有差异)
						.get();																			//得到转换值
		System.out.println(personName);
		
		
		
		System.out.println("===========下面是统计程序员月薪示例===========");
		//###########获取所有程序员月薪############
		List<Integer> list = new ArrayList<>();
		for (Person person : javaProgrammers) {
			list.add(person.getSalary()) ;
		}
		//Java6 写法 ↑
		//以上代码与以下代码等价
		//Java8 写法 ↓
		IntSummaryStatistics statistics = javaProgrammers.stream()							//声明变量IntSummaryStatistics statistics(可重复使用)
														.mapToInt(Person::getSalary)
														.summaryStatistics() ;
		//###########获取所有程序员月薪############
		
		
		
		
		//###########月薪最高的程序员############
		Collections.sort(list);
		System.out.println("月薪最高的程序员:"+list.get(list.size()-1));
		//Java6 写法 ↑
		//以上代码与以下代码等价
		//Java8 写法 ↓
		System.out.println("月薪最高的程序员:"+statistics.getMax());
		//###########月薪最高的程序员############
		
		
		
		
		//###########所有程序员月薪总和############
		Integer totalSalary = 0 ;
		for (Integer salary : list) {
			totalSalary += salary ;
		}
		System.out.println("所有程序员月薪总和:"+totalSalary);
		//Java6 写法 ↑
		//以上代码与以下代码等价
		//Java8 写法 ↓
		//Integer sum = javaProgrammers.stream().mapToInt(Person::getSalary).sum() ;			//单次计算
		System.out.println("所有程序员月薪总和:"+statistics.getSum());
		//###########所有程序员月薪总和############
		
		
		
		
		//###########所有程序员月薪平均数############
		System.out.println("所有程序员月薪平均数:"+totalSalary/list.size());
		//Java6 写法 ↑
		//以上代码与以下代码等价
		//Java8 写法 ↓
		System.out.println("所有程序员月薪平均数:"+statistics.getAverage());
		//###########平均数############
		
		
	}
	

	// 显示程序员信息
	private static void showPerson(Person p) {
		System.out.println(String.format("name:[%s %s] , age:[%d] , gender:[%s] , salary:[%d]", p.getFirstName(),p.getLastName(),p.getAge(),p.getGender(),p.getSalary()));
	}
}
