package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AdvertiseSpace;

/**
 * 广告位逻辑层接口
 * @author yy
 *
 */
public interface AdvertiseSpaceService {
	/** 新增广告位 */	
	public boolean addAdvertiseSpace(AdvertiseSpace advertiseSpace) ;	
	/** 删除广告位 */	
	public boolean delAdvertiseSpace(Integer advertiseSpaceId) ;
	/** 更新广告位*/	
	public boolean updateAdvertiseSpace(AdvertiseSpace advertiseSpace);
	/** 装载广告位 */	
	public AdvertiseSpace loadAdvertiseSpace(Integer advertiseSpaceId);	
	/** 广告位总共数目 */	
	public int countAll();
	/** 根据管理员广告位总共数目 */	
	public int countAllByAdminId(Integer adminId);
	/** 分页广告位信息 */
	public List<AdvertiseSpace> browsePagingAdvertiseSpace(int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 根据管理员分页广告位信息 */
	public List<AdvertiseSpace> browsePagingAdvertiseSpaceByAdminId(Integer adminId,int pageNum,int pageSize,String orderName,String orderWay) ;		

}
