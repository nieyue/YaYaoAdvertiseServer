package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Notice;

/**
 * 公告逻辑层接口
 * @author yy
 *
 */
public interface NoticeService {
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
	public List<Notice> browsePagingNotice(int pageNum,int pageSize,String orderName,String orderWay) ;
}
