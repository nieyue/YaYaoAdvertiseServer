package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Advertise;

/**
 *  广告数据库接口
 * @author yy
 *
 */
public interface AdvertiseDao {
	/** 新增广告 */	
	public boolean addAdvertise(Advertise advertise) ;	
	/** 删除广告 */	
	public boolean delAdvertise(Integer advertiseId) ;
	/** 更新广告*/	
	public boolean updateAdvertise(Advertise advertise);
	/** 装载广告 */	
	public Advertise loadAdvertise(@Param("advertiseId")Integer advertiseId);	
	/** 广告总共数目*/	
	public int countAll(@Param("adminId")Integer adminId,@Param("type")String type,@Param("subtype")String subtype,@Param("billingMode")String billingMode,@Param("region")String region);	
	/** 分页广告 */
	public List<Advertise> browsePagingAdvertise(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据金额随机一条广告 */
	public Advertise browsePagingAdvertiseSpaceShowAdvertise(@Param("type")String type,@Param("subtype")String subtype,@Param("billingMode")String billingMode,@Param("region")String region,@Param("unitPrice")Double unitPrice,@Param("status")String status) ;
	/** 根据金额随机一条广告 备胎 */
	public List<Advertise> browsePagingAdvertiseSpaceShowAdvertiseBei(@Param("type")String type,@Param("subtype")String subtype,@Param("billingMode")String billingMode,@Param("region")String region,@Param("unitPrice")Double unitPrice,@Param("status")String status) ;
	/** 根据管理员分页广告 */
	public List<Advertise> browsePagingAdvertiseByAdminId(@Param("adminId")Integer adminId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
