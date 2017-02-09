package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Jurisdiction;

/**
 * 权限数据库接口
 * @author yy
 *
 */
public interface JurisdictionDao {
	/** 新增权限*/	
	public boolean addJurisdiction(Jurisdiction jurisdiction) ;	
	/** 删除权限 */	
	public boolean delJurisdiction(Integer jurisdictionId) ;
	/** 更新权限*/	
	public boolean updateJurisdiction(Jurisdiction jurisdiction);
	/** 装载权限 */	
	public Jurisdiction loadJurisdiction(Integer jurisdictionId);
	/** 权限总共数目 */	
	public int countAll();	
	/** 权限分页信息 */
	public List<Jurisdiction> browsePagingJurisdiction(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
