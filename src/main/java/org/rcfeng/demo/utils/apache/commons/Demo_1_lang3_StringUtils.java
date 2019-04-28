package org.rcfeng.demo.utils.apache.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * Apache Commons - StringUtils
 * @author hesp
 *
 */
public class Demo_1_lang3_StringUtils {

	public static void main(String[] args) {
		
		abbreviate();
		capitalize() ;
		countMatches();
		defaultIfEmpty();
		defaultString();
		isEmpty();
		join();
		leftPad();
		substringAfter();
		length();
		
//		StringUtils.split(str)
//		StringUtils.substring(str, start, end)
	}
	
	/**
	 * 省略
	 */
	private static void abbreviate(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"省略");
		String s = "人之初，性本善。性相近，习相远。苟不教，性乃迁。教之道，贵以专。";
		printResult(s, StringUtils.abbreviate(s,10));		//最大10位
	}
	/**
	 * 首字母大写
	 */
	private static void capitalize(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"首字母大写");
		String s = "wangxiaoming";
		printResult(s, StringUtils.capitalize(s));
	}
	/**
	 * 统计出现次数
	 */
	private static void countMatches(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"统计出现次数");
		String s = "abcdfaba";
		printResult(s, String.valueOf(StringUtils.countMatches(s, "a")));
	}
	/**
	 * 字符串默认值 defaultIfEmpty String为null或""
	 */
	private static void defaultIfEmpty(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串默认值");
		String s = null;
		String s2 = "s2" ;
		printResult(s, StringUtils.defaultIfEmpty(s, "new str"));
		printResult(s2, StringUtils.defaultIfEmpty(s2, "new str"));
	}
	/**
	 * 字符串默认值  defaultString 针对String为null
	 */
	private static void defaultString(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串默认值");
		String s = null;
		printResult(s, StringUtils.defaultString(s, "new str"));
		printResult(s, StringUtils.defaultString(s));
	}
	/**
	 * 字符串非空校验   Empty = null || ""
	 */
	private static void isEmpty(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串非空校验");
		String s = null;
		String s2 = "" ;
		System.out.println(StringUtils.isEmpty(s));
		System.out.println(StringUtils.isEmpty(s2));
	}
	/**
	 * 字符串拼接
	 */
	private static void join(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串拼接");
		//Array || Iterable 
		String[] arrayStr = {"张三","李四","字符串","搜狗","百度"} ;
		System.out.println(StringUtils.join(arrayStr));
		System.out.println(StringUtils.join(arrayStr," ; "));
		System.out.println(StringUtils.join(arrayStr,"\n"));
	}
	/**
	 * 字符串向左填充
	 */
	private static void leftPad(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串向左填充");
		String s1 = "123" ;
		String s2 = "123456" ;
		System.out.println(StringUtils.leftPad(s1,10));
		System.out.println(StringUtils.leftPad(s2,10,"0"));
	}
	/**
	 * 字符串向右截取
	 */
	private static void substringAfter(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串向右截取");
		String s1 = "123456789388888" ;
		System.out.println(StringUtils.substringAfter(s1,"3"));
		System.out.println(StringUtils.substringAfterLast(s1,"3"));
	}
	/**
	 * 字符串长度
	 */
	private static void length(){
		printTitle(Thread.currentThread().getStackTrace()[1].getMethodName(),"字符串长度");
		String s1 = null ;
		String s2 = "" ;
		String s3 = "123456789388888" ;
		System.out.println(StringUtils.length(s1));
		System.out.println(StringUtils.length(s2));
		System.out.println(StringUtils.length(s3));
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 打印方法标题
	 * @param i
	 */
	private static void printTitle(String methodName,String explanation) {
		System.out.println(String.format("\n##########%s()%s##########", methodName,explanation));
	}
	/**
	 * 打印结果
	 * @param s
	 * @param r
	 */
	private static void printResult(String s,String r) {
		System.out.println(s);
		System.out.println("->");
		System.out.println(r);
	}
}
