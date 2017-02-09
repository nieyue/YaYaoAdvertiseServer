package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 * @author yy
 *
 */
public class Jurisdiction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 权限id
	 */
	private Integer jurisdictionId;
	
	/**
	 * 权限工作流名称
	 */
	private String name;
	/**
	 * 增加 true 1
	 */
	private Integer addtion;
	/**
	 * 删除 true 1
	 */
	private Integer deletion;
	/**
	 * 修改 true 1
	 */
	private Integer updation;
	/**
	 * 查询 true 1
	 */
	private Integer queries;
	/**
	 * 权限更新时间 
	 */
	private Date updateDate;
	/**
	 * 角色id
	 */
	private Integer roleId;
	
	
	public Jurisdiction() {
		super();
	}
	
	public Jurisdiction(Integer jurisdictionId, String name, Integer addtion,
			Integer deletion, Integer updation, Integer queries,
			Date updateDate, Integer roleId) {
		super();
		this.jurisdictionId = jurisdictionId;
		this.name = name;
		this.addtion = addtion;
		this.deletion = deletion;
		this.updation = updation;
		this.queries = queries;
		this.updateDate = updateDate;
		this.roleId = roleId;
	}

	public Integer getJurisdictionId() {
		return jurisdictionId;
	}
	public void setJurisdictionId(Integer jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAddtion() {
		return addtion;
	}
	public void setAddtion(Integer addtion) {
		this.addtion = addtion;
	}
	public Integer getDeletion() {
		return deletion;
	}
	public void setDeletion(Integer deletion) {
		this.deletion = deletion;
	}
	public Integer getUpdation() {
		return updation;
	}
	public void setUpdation(Integer updation) {
		this.updation = updation;
	}
	public Integer getQueries() {
		return queries;
	}
	public void setQueries(Integer queries) {
		this.queries = queries;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
