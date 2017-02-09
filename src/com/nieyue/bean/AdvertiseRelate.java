package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位广告关系
 * 
 * @author yy
 * 
 */
public class AdvertiseRelate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广告位广告关系id
	 */
	public Integer advertiseRelateId;
	/**
	 * 更新时间
	 */
	public Date updateDate;
	/**
	 * 广告位id外键
	 */
	public Integer advertiseSpaceId;
	/**
	 * 广告id外键
	 */
	public Integer advertiseId;
	
	public AdvertiseRelate(Integer advertiseRelateId, Date updateDate,
			Integer advertiseSpaceId, Integer advertiseId) {
		super();
		this.advertiseRelateId = advertiseRelateId;
		this.updateDate = updateDate;
		this.advertiseSpaceId = advertiseSpaceId;
		this.advertiseId = advertiseId;
	}
	public AdvertiseRelate() {
		super();
	}
	public Integer getAdvertiseRelateId() {
		return advertiseRelateId;
	}
	public void setAdvertiseRelateId(Integer advertiseRelateId) {
		this.advertiseRelateId = advertiseRelateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getAdvertiseSpaceId() {
		return advertiseSpaceId;
	}
	public void setAdvertiseSpaceId(Integer advertiseSpaceId) {
		this.advertiseSpaceId = advertiseSpaceId;
	}
	public Integer getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Integer advertiseId) {
		this.advertiseId = advertiseId;
	}

}
