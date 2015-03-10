package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.FrequencyDao;
import com.springmvc.relationdrug.domain.Frequency;

/**
 * Summary : 频次service
 * 
 */
@Transactional
@Service
public class FrequencyService extends BaseServiceImpl<Frequency,Long> {

	@Resource
	@Autowired
	private FrequencyDao frequencyDao;
	
	public List<Frequency>  findByNameAndSymbol(String name){
		return frequencyDao.findByNameAndSymbol(name);
	}
	public Frequency  findByName(String name){
		return frequencyDao.findByName(name);
	}
	@Override
	public BaseDao<Frequency, Long> getDao() {
		return frequencyDao;
	}
}
