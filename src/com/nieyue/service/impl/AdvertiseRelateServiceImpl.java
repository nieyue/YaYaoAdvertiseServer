package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.AdvertiseRelate;
import com.nieyue.dao.AdvertiseRelateDao;
import com.nieyue.service.AdvertiseRelateService;
@Service("advertiseRelateService")
public class AdvertiseRelateServiceImpl implements AdvertiseRelateService{
	@Resource
	AdvertiseRelateDao advertiseRelateDao;

	@Override
	public boolean addAdvertiseRelate(AdvertiseRelate advertiseRelate) {
		advertiseRelate.setUpdateDate(new Date());
		boolean b = advertiseRelateDao.addAdvertiseRelate(advertiseRelate);
		return b;
	}

	@Override
	public boolean delAdvertiseRelate(Integer advertiseRelateId) {
		boolean b = advertiseRelateDao.delAdvertiseRelate(advertiseRelateId);
		return b;
	}

	@Override
	public boolean updateAdvertiseRelate(AdvertiseRelate advertiseRelate) {
		advertiseRelate.setUpdateDate(new Date());
		boolean b = advertiseRelateDao.updateAdvertiseRelate(advertiseRelate);
		return b;
	}

	@Override
	public AdvertiseRelate loadAdvertiseRelate(Integer advertiseRelateId) {
		AdvertiseRelate r = advertiseRelateDao.loadAdvertiseRelate(advertiseRelateId);
		return r;
	}

	@Override
	public int countAll() {
		int c = advertiseRelateDao.countAll();
		return c;
	}

	@Override
	public List<AdvertiseRelate> browsePagingAdvertiseRelate(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AdvertiseRelate> l = advertiseRelateDao.browsePagingAdvertiseRelate(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
