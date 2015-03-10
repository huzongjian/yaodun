package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.DosageDao;
import com.springmvc.relationdrug.domain.Dosage;
@Transactional
@Service
public class DosageService extends BaseServiceImpl<Dosage,Long>{

	@Resource
	private DosageDao dosageDao;
	
	
	public List<Dosage> findByBasicId(Long basicid){
		return dosageDao.findByBasicId(basicid);
	}
	
	 public Dosage findByDrugIdAndUsage(Long drugId,Long usage){
		return dosageDao.findByDrugIdAndUsage(drugId,usage);
	}

	@Override
	public BaseDao<Dosage, Long> getDao() {
		return dosageDao;
	}
	

}
