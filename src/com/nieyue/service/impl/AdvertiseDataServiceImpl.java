package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.AdvertiseData;
import com.nieyue.dao.AdvertiseDataDao;
import com.nieyue.service.AdvertiseDataService;
@Service("advertiseDataService")
public class AdvertiseDataServiceImpl implements AdvertiseDataService{
	@Resource
	AdvertiseDataDao advertiseDataDao;

	@Override
	public boolean addAdvertiseData(AdvertiseData advertiseData) {
		advertiseData.setDailyDay(new Date());
		boolean b = advertiseDataDao.addAdvertiseData(advertiseData);
		return b;
	}

	@Override
	public boolean delAdvertiseData(Integer advertiseDataId) {
		boolean b = advertiseDataDao.delAdvertiseData(advertiseDataId);
		return b;
	}

	@Override
	public boolean updateAdvertiseData(AdvertiseData advertiseData) {
		boolean b = advertiseDataDao.updateAdvertiseData(advertiseData);
		return b;
	}

	@Override
	public AdvertiseData loadAdvertiseData(Integer advertiseDataId) {
		AdvertiseData r = advertiseDataDao.loadAdvertiseData(advertiseDataId);
		return r;
	}

	@Override
	public int countAll() {
		int c = advertiseDataDao.countAll();
		return c;
	}

	@Override
	public List<AdvertiseData> browsePagingAdvertiseData(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AdvertiseData> l = advertiseDataDao.browsePagingAdvertiseData(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public AdvertiseData loadAdvertiseDataByAdvertiseIdAndDailyDay(
			Integer advertiseId, Date dailyDay) {
		AdvertiseData r = advertiseDataDao.loadAdvertiseDataByAdvertiseIdAndDailyDay(advertiseId, dailyDay);
		return r;
	}

	@Override
	public List<AdvertiseData> browsePagingAdvertiseDataByAdvertiseIdAndDailyDays(
			Integer advertiseId, Date startDailyDay, Date endDailyDay,
			String orderName, String orderWay) {
		List<AdvertiseData> l = advertiseDataDao.browsePagingAdvertiseDataByAdvertiseIdAndDailyDays(advertiseId, startDailyDay, endDailyDay, orderName, orderWay);
		return l;
	}

	
}
