package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.DrugTypeDao;
import com.springmvc.relationdrug.domain.DrugType;
import com.springmvc.relationdrug.service.DrugTypeService;
@Service
public class DrugTypeServiceImpl extends BaseServiceImpl<DrugType, Long> implements DrugTypeService{

	@Resource
	DrugTypeDao drugTypeDao;
	@Override
	public BaseDao<DrugType, Long> getDao() {
		// TODO Auto-generated method stub
		return drugTypeDao;
	}
	public List<DrugType> findBydrugids(List<Long> drugids) {
		// TODO Auto-generated method stub
		return drugTypeDao.findBydrugids(drugids);
	}

}
