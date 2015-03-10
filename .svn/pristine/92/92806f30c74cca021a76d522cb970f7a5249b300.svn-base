package com.springmvc.relationdrug.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.CheckUpdateDao;
import com.springmvc.relationdrug.domain.CheckUpdate;
import com.springmvc.relationdrug.service.CheckUpdateService;
@Transactional
@Service
public class CheckUpateServiceImpl extends BaseServiceImpl<CheckUpdate, Long> implements CheckUpdateService{

	
	@Resource
	private CheckUpdateDao checkUpdateDao;
	@Override
	public BaseDao<CheckUpdate, Long> getDao() {
		// TODO Auto-generated method stub
		return checkUpdateDao;
	}

}
