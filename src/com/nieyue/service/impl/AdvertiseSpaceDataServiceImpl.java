package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.AdvertiseSpaceData;
import com.nieyue.dao.AdvertiseSpaceDataDao;
import com.nieyue.service.AdvertiseSpaceDataService;
@Service("advertiseSpaceDataService")
public class AdvertiseSpaceDataServiceImpl implements AdvertiseSpaceDataService{
	@Resource
	AdvertiseSpaceDataDao advertiseSpaceDataDao;

	@Override
	public boolean addAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData) {
		advertiseSpaceData.setDailyDay(new Date());
		boolean b = advertiseSpaceDataDao.addAdvertiseSpaceData(advertiseSpaceData);
		return b;
	}

	@Override
	public boolean delAdvertiseSpaceData(Integer advertiseSpaceDataId) {
		boolean b = advertiseSpaceDataDao.delAdvertiseSpaceData(advertiseSpaceDataId);
		return b;
	}

	@Override
	public boolean updateAdvertiseSpaceData(AdvertiseSpaceData advertiseSpaceData) {
		boolean b = advertiseSpaceDataDao.updateAdvertiseSpaceData(advertiseSpaceData);
		return b;
	}

	@Override
	public AdvertiseSpaceData loadAdvertiseSpaceData(Integer advertiseSpaceDataId) {
		AdvertiseSpaceData r = advertiseSpaceDataDao.loadAdvertiseSpaceData(advertiseSpaceDataId);
		return r;
	}

	@Override
	public int countAll() {
		int c = advertiseSpaceDataDao.countAll();
		return c;
	}

	@Override
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceData(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AdvertiseSpaceData> l = advertiseSpaceDataDao.browsePagingAdvertiseSpaceData(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public AdvertiseSpaceData loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(
			Integer advertiseSpaceId, Date dailyDay) {
		AdvertiseSpaceData r = advertiseSpaceDataDao.loadAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDay(advertiseSpaceId, dailyDay);
		return r;
	}

	@Override
	public List<AdvertiseSpaceData> browsePagingAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDays(
			Integer advertiseSpaceId, Date startDailyDay, Date endDailyDay,
			String orderName, String orderWay) {
		List<AdvertiseSpaceData> l = advertiseSpaceDataDao.browsePagingAdvertiseSpaceDataByAdvertiseSpaceIdAndDailyDays(advertiseSpaceId, startDailyDay, endDailyDay, orderName, orderWay);
		return l;
	}

	
}
