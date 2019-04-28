package org.rcfeng.demo.vo;

public class People {

	private String name ;
	private Integer age ;
	private Integer qq ;
	
	public People() {
	}
	
	public People(String name, Integer age, Integer qq) {
	super();
	this.name = name;
	this.age = age;
	this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}
}
