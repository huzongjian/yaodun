package com.springmvc.relationdrug.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.ThirdPartyDao;
import com.springmvc.relationdrug.domain.ThirdParty;
import com.springmvc.relationdrug.service.ThirdPartyService;
@Service
public class ThirdPartyServiceImpl extends BaseServiceImpl<ThirdParty, Long> implements ThirdPartyService{
    @Resource
	public ThirdPartyDao thirdPartyDao;
	@Override
	public BaseDao<ThirdParty, Long> getDao() {
		// TODO Auto-generated method stub
		return thirdPartyDao;
	}

}
