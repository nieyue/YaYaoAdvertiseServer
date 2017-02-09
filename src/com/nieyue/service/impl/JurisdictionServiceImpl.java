package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Jurisdiction;
import com.nieyue.dao.JurisdictionDao;
import com.nieyue.service.JurisdictionService;
@Service("jurisdictionService")
public class JurisdictionServiceImpl implements JurisdictionService{
	@Resource
	JurisdictionDao jurisdictionDao;

	@Override
	public boolean addJurisdiction(Jurisdiction jurisdiction) {
		jurisdiction.setUpdateDate(new Date());
		boolean b = jurisdictionDao.addJurisdiction(jurisdiction);
		return b;
	}

	@Override
	public boolean delJurisdiction(Integer jurisdictionId) {
		boolean b = jurisdictionDao.delJurisdiction(jurisdictionId);
		return b;
	}

	@Override
	public boolean updateJurisdiction(Jurisdiction jurisdiction) {
		jurisdiction.setUpdateDate(new Date());
		boolean b = jurisdictionDao.updateJurisdiction(jurisdiction);
		return b;
	}

	@Override
	public Jurisdiction loadJurisdiction(Integer jurisdictionId) {
		Jurisdiction r = jurisdictionDao.loadJurisdiction(jurisdictionId);
		return r;
	}

	@Override
	public int countAll() {
		int c = jurisdictionDao.countAll();
		return c;
	}

	@Override
	public List<Jurisdiction> browsePagingJurisdiction(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Jurisdiction> l = jurisdictionDao.browsePagingJurisdiction(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	
}
