package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.DrugDiseaseDao;
import com.springmvc.relationdrug.domain.DrugDisease;

/**
 */
@Transactional
@Repository
public class DrugDiseaseDaoImpl extends BaseDaoImpl<DrugDisease, Long>
		implements DrugDiseaseDao {

	@Resource
	public SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<DrugDisease> findDrugDiseaseByIcd(String icd, long fLevel) {
		StringBuffer hql = new StringBuffer();
		hql.append("from DrugDisease t where t.forbidIcd10 = ? and t.forbidLevel = ?");
		List<DrugDisease> list = this.getSession().createQuery(hql.toString())
				.setString(0, icd).setLong(1, fLevel).list();
		return list;
	}

}
