package com.nieyue.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 日加人数
 * @author yy
 *
 */
public class DaysTask implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;//名称
	   public String daysTotalNumber;//当天总加人数
	   public String daysRetainNumber;//当天留存人数
	   public Date daysDate;//日期
	   
	   
	public DaysTask() {
		super();
	}
	public DaysTask(String name, String daysTotalNumber,
			String daysRetainNumber, Date daysDate) {
		super();
		this.name = name;
		this.daysTotalNumber = daysTotalNumber;
		this.daysRetainNumber = daysRetainNumber;
		this.daysDate = daysDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDaysTotalNumber() {
		return daysTotalNumber;
	}
	public void setDaysTotalNumber(String daysTotalNumber) {
		this.daysTotalNumber = daysTotalNumber;
	}
	public String getDaysRetainNumber() {
		return daysRetainNumber;
	}
	public void setDaysRetainNumber(String daysRetainNumber) {
		this.daysRetainNumber = daysRetainNumber;
	}
	public Date getDaysDate() {
		return daysDate;
	}
	public void setDaysDate(Date daysDate) {
		this.daysDate = daysDate;
	}
	   
}
