package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告数据
 * @author yy
 *
 */
public class AdvertiseData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer advertiseDataId;
	/**
	 * 浏览次数，即页面浏览量或点击量，用户每次刷新即被计算一次
	 */
	private Long pvs;
	/**
	 * 独立访客访问您网站的一台电脑客户端为一个访客。00:00-24:00内相同的客户端只被计算一次。
	 */
	private Long uvs;
	/**
	 * 独立IP数，00:00-24:00内相同IP地         址之被计算一次
	 */
	private Long ips;
	/**
	 * 转发数
	 */
	private Long forward;
	/**
	 * 每日日期
	 */
	private Date dailyDay;
	/**
	 * 广告id
	 */
	private Integer advertiseId;
	public AdvertiseData(Integer advertiseDataId, Long pvs, Long uvs, Long ips,
			Long forward, Date dailyDay, Integer advertiseId) {
		super();
		this.advertiseDataId = advertiseDataId;
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.forward = forward;
		this.dailyDay = dailyDay;
		this.advertiseId = advertiseId;
	}
	public AdvertiseData() {
		super();
	}
	public Integer getAdvertiseDataId() {
		return advertiseDataId;
	}
	public void setAdvertiseDataId(Integer advertiseDataId) {
		this.advertiseDataId = advertiseDataId;
	}
	public Long getPvs() {
		return pvs;
	}
	public void setPvs(Long pvs) {
		this.pvs = pvs;
	}
	public Long getUvs() {
		return uvs;
	}
	public void setUvs(Long uvs) {
		this.uvs = uvs;
	}
	public Long getIps() {
		return ips;
	}
	public void setIps(Long ips) {
		this.ips = ips;
	}
	public Long getForward() {
		return forward;
	}
	public void setForward(Long forward) {
		this.forward = forward;
	}
	public Date getDailyDay() {
		return dailyDay;
	}
	public void setDailyDay(Date dailyDay) {
		this.dailyDay = dailyDay;
	}
	public Integer getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
	}

}
