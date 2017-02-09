package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位数据
 * @author yy
 *
 */
public class AdvertiseSpaceData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer advertiseSpaceDataId;
	/**
	 * 浏览次数，即页面浏览量或点击量，用户每次刷新即被计算一次
	 */
	private Long pvs;
	/**
	 * 独立访客访问您网站的一台电脑客户端为一个访客。00:00-24:00内相同的客户端只被计算一次。
	 */
	private Long uvs;
	/**
	 * 独立IP数，00:00-24:00内相同IP地址之被计算一次
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
	 * 广告为id
	 */
	private Integer advertiseSpaceId;
	
	public AdvertiseSpaceData() {
		super();
	}
	public AdvertiseSpaceData(Integer advertiseSpaceDataId, Long pvs, Long uvs, Long ips,
			Long forward, Date dailyDay, Integer advertiseSpaceId) {
		super();
		this.advertiseSpaceDataId = advertiseSpaceDataId;
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.forward = forward;
		this.dailyDay = dailyDay;
		this.advertiseSpaceId = advertiseSpaceId;
	}
	public Integer getAdvertiseSpaceDataId() {
		return advertiseSpaceDataId;
	}
	public void setAdvertiseSpaceDataId(Integer advertiseSpaceDataId) {
		this.advertiseSpaceDataId = advertiseSpaceDataId;
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
	public Integer getAdvertiseSpaceId() {
		return advertiseSpaceId;
	}
	public void setAdvertiseSpaceId(Integer advertiseSpaceId) {
		this.advertiseSpaceId = advertiseSpaceId;
	}
	
}
