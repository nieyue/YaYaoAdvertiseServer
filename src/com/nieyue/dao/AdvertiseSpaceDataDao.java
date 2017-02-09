package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseSpaceData;

/**
 * 广告位数据数据库接口
 * @author yy
 *
 */
public interface AdvertiseSpaceDataDao {
	/** 新增广告位数据*/	
	public boolean addAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData) ;	
	/** 删除广告位数据 */	
	public boolean delAdvertiseSpaceData(Integer advertiseSpaceDataId) ;
	/** 更新广告位数据*/	
	public boolean updateAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData);
	/** 装载广告位数据 */	
	public AdvertiseSpaceData loadAdvertiseSpaceData(Integer advertiseSpaceDataId);	
	/** 根据广告位Id和时间装载广告位数据 */	
	public AdvertiseSpaceData loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(@Param("advertiseSpaceId")Integer advertiseSpaceId,@Param("dailyDay")Date dailyDay);
	/** 广告位数据总共数目 */	
	public int countAll();	
	/** 分页广告位数据信息 */
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceData(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据广告位Id和时间段分页广告数据信息 */
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDays(@Param("advertiseSpaceId")Integer advertiseSpaceId,@Param("startDailyDay")Date startDailyDay,@Param("endDailyDay")Date endDailyDay,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		

}
