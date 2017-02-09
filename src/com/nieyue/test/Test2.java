package com.nieyue.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateUtils;

import com.nieyue.util.DateUtil;

public class Test2 {
public static void main(String[] args) {
	String a="";
	String b=null;
	Long aa=5544554454555555555L;
	Long bb=234234234234L;
	System.out.println(a.equals(null));
	System.out.println(aa+bb);
	System.out.println(Integer.MAX_VALUE);
	System.out.println(Calendar.getInstance().getTime());
	int cc=8;
	int ii=1;
	while (true) {
			if(ii!=cc){
				System.out.println(ii);
			}else{
				break;
			}
			ii++;
		
		
	}
  
    System.out.println(DateUtil.currentToEndTime()/60);  
   String[] aaa={"192.168.0.1","ds"};
    JSONArray ja=JSONArray.fromObject(aaa);
    System.out.println(ja.add("sdfsdf"));
    System.out.println(ja.toString());
    JSONArray nja = JSONArray.fromObject(ja.toString());
    System.out.println(nja);
    System.out.println(nja.get(1));
}


}
