package com.nieyue.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		long num=1000000l;
		List<String> list = new ArrayList<String>();
		long startlist=System.currentTimeMillis();
		System.out.println(startlist);
		for (int i = 0; i < num; i++) {
			list.add("i"+i);
		}
		long endlist=System.currentTimeMillis();
		System.out.println(endlist-startlist);
		
		List<String> list2 = new LinkedList<String>();
		long startlist2=System.currentTimeMillis();
		System.out.println(startlist2);
		for (int i = 0; i < num; i++) {
			list2.add("i"+i);
		}
		long endlist2=System.currentTimeMillis();
		System.out.println(endlist2-startlist2);
		
		
		String[] list3 = new String[(int)num];
		long startlist3=System.currentTimeMillis();
		System.out.println(startlist3);
		for (int i = 0; i < num; i++) {
			list3[i]="i"+i;
		}
		long endlist3=System.currentTimeMillis();
		System.out.println(endlist3-startlist3);
	}
}
