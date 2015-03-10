package com.springmvc.relationdrug.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.BasicDrugRelationship;

@Transactional
@Repository
public interface BasicDrugRelationshipDao extends
		BaseDao<BasicDrugRelationship, Long> {
	public List<BasicDrugRelationship> findByNameAndSymbol(String name);
}
