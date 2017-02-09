package com.nieyue.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 月加人数
 * @author yy
 *
 */
public class MonthsTask implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;//名称
	   public String monthsTotalNumber;//当月总加人数
	   public String monthsRetainNumber;//当月留存人数
	   public Date monthsDate;//日期
	public MonthsTask(String name, String monthsTotalNumber,
			String monthsRetainNumber, Date monthsDate) {
		super();
		this.name = name;
		this.monthsTotalNumber = monthsTotalNumber;
		this.monthsRetainNumber = monthsRetainNumber;
		this.monthsDate = monthsDate;
	}
	public MonthsTask() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonthsTotalNumber() {
		return monthsTotalNumber;
	}
	public void setMonthsTotalNumber(String monthsTotalNumber) {
		this.monthsTotalNumber = monthsTotalNumber;
	}
	public String getMonthsRetainNumber() {
		return monthsRetainNumber;
	}
	public void setMonthsRetainNumber(String monthsRetainNumber) {
		this.monthsRetainNumber = monthsRetainNumber;
	}
	public Date getMonthsDate() {
		return monthsDate;
	}
	public void setMonthsDate(Date monthsDate) {
		this.monthsDate = monthsDate;
	}
	   
	   
	   
}
