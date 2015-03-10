package com.springmvc.relationdrug.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.BasicDrugRelationshipDao;
import com.springmvc.relationdrug.domain.BasicDrugRelationship;
/**
 * 
  * @ClassName: BasicDrugCheckService
  * @Description: 药物基表service
  *
 */
@Transactional
@Service("basicDrugRelationshipService")
public class BasicDrugRelationshipService extends BaseServiceImpl<BasicDrugRelationship,Long>{

	
	@Resource
	@Autowired
	private BasicDrugRelationshipDao basicDrugRelationshipDao;
	
	public List<BasicDrugRelationship> findByNameAndSymbol(String name){
		List<BasicDrugRelationship> bdc = basicDrugRelationshipDao.findByNameAndSymbol(name);
		return bdc;
	}

	@Override
	public BaseDao<BasicDrugRelationship, Long> getDao() {
		return basicDrugRelationshipDao;
	}
	




}
