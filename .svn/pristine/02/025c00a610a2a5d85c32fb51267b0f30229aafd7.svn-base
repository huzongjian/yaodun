package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.OtherInfoDao;
import com.springmvc.relationdrug.domain.Otherinfo;
import com.springmvc.relationdrug.rules.service.OtherInfoService;
@Transactional
@Service("otherinfoservice")
public class OtherInfoServiceImpl extends BaseServiceImpl<Otherinfo, Long>implements OtherInfoService{

	@Resource
	private OtherInfoDao otherInfoDao;
	@Override
	public BaseDao<Otherinfo, Long> getDao() {
		// TODO Auto-generated method stub
		return otherInfoDao;
	}
	public List<Otherinfo> findbytype(Integer type) {
		return otherInfoDao.findbytype(type);
	}

}
