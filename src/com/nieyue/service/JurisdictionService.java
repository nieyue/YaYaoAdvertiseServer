package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Jurisdiction;

/**
 * 权限逻辑层接口
 * @author yy
 *
 */
public interface JurisdictionService {
	/** 新增权限 */	
	public boolean addJurisdiction(Jurisdiction jurisdiction) ;	
	/** 删除权限  */	
	public boolean delJurisdiction(Integer jurisdictionId) ;
	/** 更新权限 */	
	public boolean updateJurisdiction(Jurisdiction jurisdiction);
	/** 装载 权限 */	
	public Jurisdiction loadJurisdiction(Integer jurisdictionId);	
	/** 权限 总共数目 */	
	public int countAll();	
	/** 权限 分页信息 */
	public List<Jurisdiction> browsePagingJurisdiction(int pageNum,int pageSize,String orderName,String orderWay);		
}
