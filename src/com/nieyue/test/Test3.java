package com.nieyue.test;

import java.util.HashSet;
import java.util.Set;


public class Test3 {
	public static void main(String[] args) {
		Set<String> set =new HashSet<String>();
		boolean adsf1=set.add("adsf1");
		boolean adsf2=set.add("adsf2");
		boolean adsf3=set.add("adsf3");
		boolean adsf4=set.add("adsf4");
		boolean adsf5=set.add("adsf5");
		boolean adsf6=set.add("adsf6");
		boolean tadsf3 = set.add("adsf3");
		System.out.println(adsf1);
		System.out.println(adsf2);
		System.out.println(adsf3);
		System.out.println(adsf4);
		System.out.println(adsf5);
		System.out.println(adsf6);
		System.out.println(tadsf3);
		for (String s : set) {
			System.out.println(s);
		}
	}
}
