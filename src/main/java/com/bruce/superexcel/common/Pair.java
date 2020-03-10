/*
 * Copyright (c) 2016 by XuanBang Information Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.bruce.superexcel.common;

/**
 * First Second 组成pair
 * 
 * @author xiangshaoxu 2016年6月12日下午2:45:49
 * @version 1.0.0
 */
public class Pair<F,S> {

	F first;
	S second;
	
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return first;
	}
	public void setFirst(F first) {
		this.first = first;
	}
	public S getSecond() {
		return second;
	}
	public void setSecond(S second) {
		this.second = second;
	}
	
}
