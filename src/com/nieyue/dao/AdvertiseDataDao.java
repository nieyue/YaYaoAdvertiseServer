package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseData;

/**
 * 广告数据数据库接口
 * @author yy
 *
 */
public interface AdvertiseDataDao {
	/** 新增广告数据*/	
	public boolean addAdvertiseData(AdvertiseData advertiseData) ;	
	/** 删除广告数据 */	
	public boolean delAdvertiseData(Integer advertiseDataId) ;
	/** 更新广告数据*/	
	public boolean updateAdvertiseData(AdvertiseData advertiseData);
	/** 装载广告数据 */	
	public AdvertiseData loadAdvertiseData(Integer advertiseDataId);
	/** 根据广告Id和时间装载广告数据 */	
	public AdvertiseData loadAdvertiseDataByAdvertiseIdAndDailyDay(@Param("advertiseId")Integer advertiseId,@Param("dailyDay")Date dailyDay);
	/** 广告数据总共数目 */	
	public int countAll();	
	/** 分页广告数据信息 */
	public List<AdvertiseData> browsePagingAdvertiseData(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据广告Id和时间段分页广告数据信息 */
	public List<AdvertiseData> browsePagingAdvertiseDataByAdvertiseIdAndDailyDays(@Param("advertiseId")Integer advertiseId,@Param("startDailyDay")Date startDailyDay,@Param("endDailyDay")Date endDailyDay,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		

}
