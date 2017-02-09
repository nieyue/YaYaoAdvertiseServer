package com.nieyue.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 状态码常量类
 * @author yy
 *
 */
public class Timer {
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public static String GetValueByKey(String key){
		 Locale locale = Locale.getDefault();  
         ResourceBundle localResource = ResourceBundle.getBundle("config/timer", locale);
         try {
        	 String value = localResource.getString(key); 
        	 return value;
			
		} catch (Exception e) {
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(Timer.GetValueByKey("SUCCESS"));
		//System.out.println(SUCCESS);
	}
	/**
	 * 开始时间
	 */
	public static String START_TIME="START_TIME";
	/**
	 * 睡眠时间
	 */
	public static String SLEEP_TIME="SLEEP_TIME";
	
}
