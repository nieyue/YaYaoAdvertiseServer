package com.nieyue.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 周加人数
 * @author yy
 *
 */
public class WeeksTask implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;//名称
	   public String weeksTotalNumber;//当周总加人数
	   public String weeksRetainNumber;//当周留存人数
	   public Date weeksDate;//日期
	public WeeksTask(String name, String weeksTotalNumber,
			String weeksRetainNumber, Date weeksDate) {
		super();
		this.name = name;
		this.weeksTotalNumber = weeksTotalNumber;
		this.weeksRetainNumber = weeksRetainNumber;
		this.weeksDate = weeksDate;
	}
	public WeeksTask() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeeksTotalNumber() {
		return weeksTotalNumber;
	}
	public void setWeeksTotalNumber(String weeksTotalNumber) {
		this.weeksTotalNumber = weeksTotalNumber;
	}
	public String getWeeksRetainNumber() {
		return weeksRetainNumber;
	}
	public void setWeeksRetainNumber(String weeksRetainNumber) {
		this.weeksRetainNumber = weeksRetainNumber;
	}
	public Date getWeeksDate() {
		return weeksDate;
	}
	public void setWeeksDate(Date weeksDate) {
		this.weeksDate = weeksDate;
	}
	   
	   
}
