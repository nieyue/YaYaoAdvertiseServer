package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Advertise;
import com.nieyue.dao.AdvertiseDao;
import com.nieyue.service.AdvertiseService;
@Service("advertiseService")
public class AdvertiseServiceImpl implements AdvertiseService{
	@Resource
	AdvertiseDao advertiseDao;
	@Override
	public boolean addAdvertise(Advertise advertise) {
		advertise.setUpdateDate(new Date());
		advertise.setNowUnitDeliveryNumber(0);
		advertise.setNowUnitMoney(0.00);
		boolean b = advertiseDao.addAdvertise(advertise);
		return b;
	}

	@Override
	public boolean delAdvertise(Integer advertiseId) {
		boolean b = advertiseDao.delAdvertise(advertiseId);
		return b;
	}

	@Override
	public boolean updateAdvertise(Advertise advertise) {
		advertise.setUpdateDate(new Date());
		boolean b =advertiseDao.updateAdvertise(advertise);
		return b;
	}

	@Override
	public Advertise loadAdvertise(Integer advertiseId) {
		 Advertise p = advertiseDao.loadAdvertise(advertiseId);
		return p;
	}

	@Override
	public int countAll() {
		int p = advertiseDao.countAll();
		return p;
	}


	@Override
	public List<Advertise> browsePagingAdvertise(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Advertise> l = advertiseDao.browsePagingAdvertise(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public int countAllByAdminId(Integer adminId) {
		int p = advertiseDao.countAllByAdminId(adminId);
		return p;
	}

	@Override
	public List<Advertise> browsePagingAdvertiseByAdminId(Integer adminId,
			int pageNum, int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Advertise> l = advertiseDao.browsePagingAdvertiseByAdminId(adminId, pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	@Override
	public Advertise browsePagingAdvertiseSpaceShowAdvertise(Double unitPrice,String status,String type) {
		Advertise a = advertiseDao.browsePagingAdvertiseSpaceShowAdvertise(unitPrice,status,type);
		return a;
	}

	@Override
	public List<Advertise> browsePagingAdvertiseSpaceShowAdvertiseBei(
			Double unitPrice, String status,String type) {
		List<Advertise> a = advertiseDao.browsePagingAdvertiseSpaceShowAdvertiseBei(unitPrice, status,type);
		return a;
	}

}
