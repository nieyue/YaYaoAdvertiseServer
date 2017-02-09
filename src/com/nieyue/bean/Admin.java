package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员类
 * @author yy
 *
 */
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 管理员id
	 */
	private Integer adminId;
	
	/**
	 * 管理者姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String cellPhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 金钱
	 */
	private Double money;
	/**
	 * 提现金钱
	 */
	private Double withdrawals;
	/**
	 * 充值金钱
	 */
	private Double recharge;
	/**
	 * 身份证
	 */
	private String identityCards;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 微信号
	 */
	private String wechat;
	/**
	 * 开户人
	 */
	private String bankUserName;
	/**
	 * 开户银行
	 */
	private String bankName;
	/**
	 *银行账号
	 */
	private String bankAccount;
	/**
	 *开户银行地址
	 */
	private String bankAddress;
	/**
	 *账号状态
	 */
	private String status;
	/**
	 * 账号创建时间
	 */
	private Date createDate;
	/**
	 * 最新登录时间
	 */
	private Date lastLoginDate;
	
	/**
	 *角色id外键
	 */
	private Integer roleId;
	
	public Admin() {
		super();
	}

	public Admin(Integer adminId, String name, String cellPhone, String email,
			String password, Double money,Double withdrawals,Double recharge, String identityCards,
			String qq,String wechat, String bankUserName, String bankName,
			String bankAccount, String bankAddress, String status,
			Date createDate, Date lastLoginDate, Integer roleId) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.cellPhone = cellPhone;
		this.email = email;
		this.password = password;
		this.money = money;
		this.withdrawals = withdrawals;
		this.recharge = recharge;
		this.identityCards = identityCards;
		this.qq = qq;
		this.wechat = wechat;
		this.bankUserName = bankUserName;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.bankAddress = bankAddress;
		this.status = status;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
		this.roleId = roleId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getIdentityCards() {
		return identityCards;
	}

	public void setIdentityCards(String identityCards) {
		this.identityCards = identityCards;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Double getWithdrawals() {
		return withdrawals;
	}

	public void setWithdrawals(Double withdrawals) {
		this.withdrawals = withdrawals;
	}

	public Double getRecharge() {
		return recharge;
	}

	public void setRecharge(Double recharge) {
		this.recharge = recharge;
	}

}
