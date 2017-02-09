package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AdvertiseRelate;

/**
 * 广告位广告关系逻辑层接口
 * @author yy
 *
 */
public interface AdvertiseRelateService {
	/** 新增广告位广告关系 */	
	public boolean addAdvertiseRelate(AdvertiseRelate advertiseRelate) ;	
	/** 删除广告位广告关系 */	
	public boolean delAdvertiseRelate(Integer advertiseRelateId) ;
	/** 更新广告位广告关系*/	
	public boolean updateAdvertiseRelate(AdvertiseRelate AdvertiseRelate);
	/** 装载广告位广告关系 */	
	public AdvertiseRelate loadAdvertiseRelate(Integer AdvertiseRelateId);	
	/** 广告位广告关系总共数目 */	
	public int countAll();
	/** 分页广告位广告关系信息 */
	public List<AdvertiseRelate> browsePagingAdvertiseRelate(int pageNum,int pageSize,String orderName,String orderWay) ;
}
