package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.AdvertiseSpace;
import com.nieyue.dao.AdvertiseSpaceDao;
import com.nieyue.service.AdvertiseSpaceService;
@Service("advertiseSpaceService")
public class AdvertiseSpaceServiceImpl implements AdvertiseSpaceService{
	@Resource
	AdvertiseSpaceDao advertiseSpaceDao;

	@Override
	public boolean addAdvertiseSpace(AdvertiseSpace advertiseSpace) {
		advertiseSpace.setUpdateDate(new Date());
		advertiseSpace.setNowUnitDeliveryNumber(0);
		advertiseSpace.setNowUnitMoney(0.0);
		boolean b = advertiseSpaceDao.addAdvertiseSpace(advertiseSpace);
		return b;
	}

	@Override
	public boolean delAdvertiseSpace(Integer advertiseSpaceId) {
		boolean b = advertiseSpaceDao.delAdvertiseSpace(advertiseSpaceId);
		return b;
	}

	@Override
	public boolean updateAdvertiseSpace(AdvertiseSpace advertiseSpace) {
		advertiseSpace.setUpdateDate(new Date());
		boolean b = advertiseSpaceDao.updateAdvertiseSpace(advertiseSpace);
		return b;
	}

	@Override
	public AdvertiseSpace loadAdvertiseSpace(Integer advertiseSpaceId) {
		AdvertiseSpace r = advertiseSpaceDao.loadAdvertiseSpace(advertiseSpaceId);
		return r;
	}

	@Override
	public int countAll(Integer adminId,String type,String businessType,String billingMode,String region) {
		int c = advertiseSpaceDao.countAll(adminId,type,businessType,billingMode,region);
		return c;
	}

	@Override
	public List<AdvertiseSpace> browsePagingAdvertiseSpace(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AdvertiseSpace> l = advertiseSpaceDao.browsePagingAdvertiseSpace(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public List<AdvertiseSpace> browsePagingAdvertiseSpaceByAdminId(
			Integer adminId, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AdvertiseSpace> l = advertiseSpaceDao.browsePagingAdvertiseSpaceByAdminId(adminId, pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
