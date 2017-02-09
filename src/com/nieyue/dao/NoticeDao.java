package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Notice;

/**
 *  公告数据库接口
 * @author yy
 *
 */
public interface NoticeDao {
	/** 新增公告 */	
	public boolean addNotice(Notice notice) ;	
	/** 删除公告 */	
	public boolean delNotice(Integer noticeId) ;
	/** 更新公告*/	
	public boolean updateNotice(Notice notice);
	/** 装载公告 */	
	public Notice loadNotice(Integer noticeId);	
	/** 公告总共数目 */	
	public int countAll();	
	/** 分页公告 */
	public List<Notice> browsePagingNotice(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
