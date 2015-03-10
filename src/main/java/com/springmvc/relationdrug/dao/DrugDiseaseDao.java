package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.DrugDisease;

public interface DrugDiseaseDao extends BaseDao<DrugDisease, Long> {

	public List<DrugDisease> findDrugDiseaseByIcd(String icd, long fLevel);
}
