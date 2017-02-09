package com.nieyue.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;




public class SimpleSpringQuartz {
	public static void main(String[] args) {
		 new ClassPathXmlApplicationContext(new String[]{"config/spring-dao.xml","config/spring-service.xml","config/spring-timer.xml"});
	}
}
