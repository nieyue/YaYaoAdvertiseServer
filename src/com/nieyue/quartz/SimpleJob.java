package com.nieyue.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;



public class SimpleJob extends StatefulMethodInvokingJob {
	int a=1;

	@Override
	protected void executeInternal(JobExecutionContext jec)
			throws JobExecutionException {
		String message = jec.getJobDetail().getJobDataMap().getString("message");
		System.out.println(message);
	}
	public void dowork() {
		System.out.println("doworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdoworkdowork");
		
	}
	public void dowork2() {
		a++;
		System.out.println("dowork"+a);
		System.out.println(new Date().toLocaleString());
	}
}
