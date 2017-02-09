package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Notice;
import com.nieyue.dao.NoticeDao;
import com.nieyue.service.NoticeService;
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	@Resource
	NoticeDao noticeDao;
	@Override
	public boolean addNotice(Notice notice) {
		notice.setUpdateDate(new Date());
		boolean b = noticeDao.addNotice(notice);
		return b;
	}

	@Override
	public boolean delNotice(Integer noticeId) {
		boolean b = noticeDao.delNotice(noticeId);
		return b;
	}

	@Override
	public boolean updateNotice(Notice notice) {
		notice.setUpdateDate(new Date());
		boolean b = noticeDao.updateNotice(notice);
		return b;
	}

	@Override
	public Notice loadNotice(Integer noticeId) {
		 Notice p = noticeDao.loadNotice(noticeId);
		return p;
	}

	@Override
	public int countAll() {
		int p = noticeDao.countAll();
		return p;
	}


	@Override
	public List<Notice> browsePagingNotice(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Notice> l = noticeDao.browsePagingNotice(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

}
