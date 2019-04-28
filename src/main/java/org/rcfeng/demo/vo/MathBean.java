package org.rcfeng.demo.vo;

public class MathBean {

	private int i ;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	public void add(int num) {
		this.i += num ;
	}
	
	public void sub(int num) {
		this.i -= num ;
	}
	
}
