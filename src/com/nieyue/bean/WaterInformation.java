package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 流水信息
 * @author yy
 *
 */
public class WaterInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 流水id
	 */
	private Integer waterInformationId;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 金钱
	 */
	private Double money;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 用户
	 */
	private Integer adminId;

	public WaterInformation() {
		super();
	}

	public WaterInformation(Integer waterInformationId, String name,
			String type, Double money, Date createDate, Integer adminId) {
		super();
		this.waterInformationId = waterInformationId;
		this.name = name;
		this.type = type;
		this.money = money;
		this.createDate = createDate;
		this.adminId = adminId;
	}

	public Integer getWaterInformationId() {
		return waterInformationId;
	}

	public void setWaterInformationId(Integer waterInformationId) {
		this.waterInformationId = waterInformationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
}
