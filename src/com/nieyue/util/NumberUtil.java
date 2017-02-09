package com.nieyue.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字类
 * @author yy
 *
 */
public class NumberUtil {
	/**
	 * 是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		   Pattern pattern = Pattern.compile("[1-9][0-9]*$");
		   if(str==null){
			   return false;
		   }
		   Matcher isNum = pattern.matcher(str);
		   if(!isNum.matches() ){
		       return false;
		   }
		   return true;
		}
	public static void main(String[] args) {
		String aa="1435435";
		System.out.println(NumberUtil.isNumeric(aa));
	}
}

