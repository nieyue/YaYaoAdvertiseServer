package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.WaterInformation;

/**
 * 流水信息逻辑层接口
 * @author yy
 *
 */
public interface WaterInformationService {
	/** 新增流水信息 */	
	public boolean addWaterInformation(WaterInformation waterInformation) ;	
	/** 删除流水信息 */	
	public boolean delWaterInformation(Integer waterInformationId) ;
	/** 更新流水信息*/	
	public boolean updateWaterInformation(WaterInformation waterInformation);
	/** 装载流水信息 */	
	public WaterInformation loadWaterInformation(Integer waterInformationId);	
	/** 流水信息总共数目 */	
	public int countAll();
	/** 根据管理员流水信息总共数目 */	
	public int countAllByAdminId(Integer adminId);
	/** 根据管理员Id和时间装载流水信息*/	
	public WaterInformation loadWaterInformationByAdminIdAndCreateDate(Integer adminId,Date createDate);
	/** 根据管理员分页流水信息 */
	public List<WaterInformation> browsePagingWaterInformationByAdminId(Integer adminId,int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 分页流水信息信息 */
	public List<WaterInformation> browsePagingWaterInformation(int pageNum,int pageSize,String orderName,String orderWay) ;
}
