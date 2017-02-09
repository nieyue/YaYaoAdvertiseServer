package com.nieyue.quartz;

import java.util.Date;

import javax.annotation.Resource;

import com.nieyue.bean.AdvertiseSpace;
import com.nieyue.service.AdvertiseSpaceService;

/**
 * 广告位工作
 * @author yy
 *
 */
public class AdvertiseSpaceJob {
	@Resource
	private AdvertiseSpaceService advertiseSpaceService;
	/**
	 * 每天增加钱
	 */
	public void addMoney(){
		//AdvertiseSpace advertiseSpace = advertiseSpaceService.brow
		System.out.println(new Date().toLocaleString());
	}
}
