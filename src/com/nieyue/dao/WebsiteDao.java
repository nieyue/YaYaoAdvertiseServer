package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Website;

/**
 * 网站数据库接口
 * @author yy
 *
 */
public interface WebsiteDao {
	/** 新增网站*/	
	public boolean addWebsite(Website website) ;	
	/** 删除网站 */	
	public boolean delWebsite(Integer websiteId) ;
	/** 更新网站*/	
	public boolean updateWebsite(Website website);
	/** 装载网站 */	
	public Website loadWebsite(Integer websiteId);	
	/** 网站总共数目 */	
	public int countAll();
	/** 根据管理员网站信息总共数目 */	
	public int countAllByAdminId(Integer adminId);
	/** 分页网站信息 */
	public List<Website> browsePagingWebsite(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据管理员分页网站信息 */
	public List<Website> browsePagingWebsiteByAdminId(@Param("adminId")Integer adminId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		

}
