package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告
 * @author yy
 *
 */
public class Advertise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广告id
	 */
	private Integer advertiseId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 子类型
	 */
	private String subtype;
	/**
	 * 计费方式
	 */
	private String billingMode;
	/**
	 * 地域
	 */
	private String region;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 广告图片
	 */
	private String img;
	/**
	 * 链接地址
	 */
	private String link;
	/**
	 * 单价
	 */
	private Double unitPrice;
	/**
	 * 投放次数
	 */
	private Integer unitDeliveryNumber;
	/**
	 * 消耗的投放次数
	 */
	private Integer nowUnitDeliveryNumber;
	/**
	 * 广告金额
	 */
	private Double unitMoney;
	/**
	 * 消耗的广告金额
	 */
	private Double nowUnitMoney;
	/**
	 * 广告状态
	 */
	private String status;
	/**
	 * 投放开始时间
	 */
	private Date startDeliveryDate;
	/**
	 * 投放结束时间
	 */
	private Date endDeliveryDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 管理员id外键
	 */
	private Integer adminId;
	
	
	public Advertise() {
		super();
	}


	public Advertise(Integer advertiseId, String name, String type,
			String subtype,String billingMode,String region,String title,
			String img, String link, Double unitPrice,
			Integer unitDeliveryNumber, Integer nowUnitDeliveryNumber,
			Double unitMoney, Double nowUnitMoney, String status,
			Date startDeliveryDate, Date endDeliveryDate, Date updateDate,
			Integer adminId) {
		super();
		this.advertiseId = advertiseId;
		this.name = name;
		this.billingMode=billingMode;
		this.type = type;
		this.subtype = subtype;
		this.region=region;
		this.title=title;
		this.img = img;
		this.link = link;
		this.unitPrice = unitPrice;
		this.unitDeliveryNumber = unitDeliveryNumber;
		this.nowUnitDeliveryNumber = nowUnitDeliveryNumber;
		this.unitMoney = unitMoney;
		this.nowUnitMoney = nowUnitMoney;
		this.status = status;
		this.startDeliveryDate = startDeliveryDate;
		this.endDeliveryDate = endDeliveryDate;
		this.updateDate = updateDate;
		this.adminId = adminId;
	}





	public Integer getAdvertiseId() {
		return advertiseId;
	}


	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
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


	public String getSubtype() {
		return subtype;
	}


	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public Double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Integer getUnitDeliveryNumber() {
		return unitDeliveryNumber;
	}


	public void setUnitDeliveryNumber(Integer unitDeliveryNumber) {
		this.unitDeliveryNumber = unitDeliveryNumber;
	}


	public Double getUnitMoney() {
		return unitMoney;
	}


	public void setUnitMoney(Double unitMoney) {
		this.unitMoney = unitMoney;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getStartDeliveryDate() {
		return startDeliveryDate;
	}


	public void setStartDeliveryDate(Date startDeliveryDate) {
		this.startDeliveryDate = startDeliveryDate;
	}


	public Date getEndDeliveryDate() {
		return endDeliveryDate;
	}


	public void setEndDeliveryDate(Date endDeliveryDate) {
		this.endDeliveryDate = endDeliveryDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	public Integer getNowUnitDeliveryNumber() {
		return nowUnitDeliveryNumber;
	}


	public void setNowUnitDeliveryNumber(Integer nowUnitDeliveryNumber) {
		this.nowUnitDeliveryNumber = nowUnitDeliveryNumber;
	}


	public Double getNowUnitMoney() {
		return nowUnitMoney;
	}


	public void setNowUnitMoney(Double nowUnitMoney) {
		this.nowUnitMoney = nowUnitMoney;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getBillingMode() {
		return billingMode;
	}


	public void setBillingMode(String billingMode) {
		this.billingMode = billingMode;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}



}
