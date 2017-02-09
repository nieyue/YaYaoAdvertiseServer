package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AdvertiseRelate;

/**
 * 广告位广告关系数据库接口
 * @author yy
 *
 */
public interface AdvertiseRelateDao {
	/** 新增广告位广告关系*/	
	public boolean addAdvertiseRelate(AdvertiseRelate advertiseRelate) ;	
	/** 删除广告位广告关系 */	
	public boolean delAdvertiseRelate(Integer advertiseRelateId) ;
	/** 更新广告位广告关系*/	
	public boolean updateAdvertiseRelate(AdvertiseRelate advertiseRelate);
	/** 装载广告位广告关系 */	
	public AdvertiseRelate loadAdvertiseRelate(Integer advertiseRelateId);	
	/** 广告位广告关系总共数目 */	
	public int countAll();	
	/** 分页广告位广告关系信息 */
	public List<AdvertiseRelate> browsePagingAdvertiseRelate(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
