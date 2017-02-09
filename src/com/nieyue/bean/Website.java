package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 网站
 * @author yy
 *
 */
public class Website implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网站id
	 */
	private Integer websiteId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 网站url
	 */
	private String url;
	/**
	 * 网站状态
	 */
	private String status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 管理员id外键
	 */
	private Integer adminId;
	
	
	public Website() {
		super();
	}


	public Website(Integer websiteId, String name, String type, String url,
			String status, String remark, Date updateDate, Integer adminId) {
		super();
		this.websiteId = websiteId;
		this.name = name;
		this.type = type;
		this.url = url;
		this.status = status;
		this.remark = remark;
		this.updateDate = updateDate;
		this.adminId = adminId;
	}


	public Integer getWebsiteId() {
		return websiteId;
	}


	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
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

}
