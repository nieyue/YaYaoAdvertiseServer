package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseSpace;

/**
 * 广告位数据库接口
 * @author yy
 *
 */
public interface AdvertiseSpaceDao {
	/** 新增广告位*/	
	public boolean addAdvertiseSpace(AdvertiseSpace advertiseSpace) ;	
	/** 删除广告位 */	
	public boolean delAdvertiseSpace(Integer advertiseSpaceId) ;
	/** 更新广告位*/	
	public boolean updateAdvertiseSpace(AdvertiseSpace advertiseSpace);
	/** 装载广告位 */	
	public AdvertiseSpace loadAdvertiseSpace(Integer advertiseSpaceId);	
	/** 广告位总共数目 */	
	public int countAll(@Param("adminId")Integer adminId,@Param("type")String type,@Param("businessType")String businessType,@Param("billingMode")String billingMode,@Param("region")String region);	
	/** 分页广告位信息 */
	public List<AdvertiseSpace> browsePagingAdvertiseSpace(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据管理员分页广告位信息 */
	public List<AdvertiseSpace> browsePagingAdvertiseSpaceByAdminId(@Param("adminId")Integer adminId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
