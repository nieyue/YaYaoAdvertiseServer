package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Website;
import com.nieyue.dao.WebsiteDao;
import com.nieyue.service.WebsiteService;
@Service("websiteService")
public class WebsiteServiceImpl implements WebsiteService{
	@Resource
	WebsiteDao websiteDao;

	@Override
	public boolean addWebsite(Website website) {
		website.setUpdateDate(new Date());
		boolean b = websiteDao.addWebsite(website);
		return b;
	}

	@Override
	public boolean delWebsite(Integer websiteId) {
		boolean b = websiteDao.delWebsite(websiteId);
		return b;
	}

	@Override
	public boolean updateWebsite(Website website) {
		website.setUpdateDate(new Date());
		boolean b = websiteDao.updateWebsite(website);
		return b;
	}

	@Override
	public Website loadWebsite(Integer websiteId) {
		Website r = websiteDao.loadWebsite(websiteId);
		return r;
	}

	@Override
	public int countAll() {
		int c = websiteDao.countAll();
		return c;
	}

	@Override
	public List<Website> browsePagingWebsite(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Website> l = websiteDao.browsePagingWebsite(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public int countAllByAdminId(Integer adminId) {
		int c = websiteDao.countAllByAdminId(adminId);
		return c;
	}

	@Override
	public List<Website> browsePagingWebsiteByAdminId(Integer adminId,
			int pageNum, int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Website> l = websiteDao.browsePagingWebsiteByAdminId(adminId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
