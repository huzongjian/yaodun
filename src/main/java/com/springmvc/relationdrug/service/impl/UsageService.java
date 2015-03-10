package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.UsageDao;
import com.springmvc.relationdrug.domain.Usage;

/**
 * Summary : 用法service
 * 
 * 
 */
@Transactional
@Service
public class UsageService extends BaseServiceImpl<Usage,Long> {


	@Resource
	private UsageDao usageDao;

	
	public List<Usage> findByNameAndSymbol(String name){
		return usageDao.findByNameAndSymbol(name);
	} 
	public Usage findByName(String name){
		return usageDao.findByName(name);
	}
	@Override
	public BaseDao<Usage, Long> getDao() {
		return usageDao;
	} 

}
