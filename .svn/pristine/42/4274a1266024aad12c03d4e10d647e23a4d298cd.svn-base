package com.springmvc.relationdrug.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.DrugSuitDiseaseDao;
import com.springmvc.relationdrug.domain.DrugSuitDisease;

/**
 * 药品适用于疾病
 */
@Transactional
@Repository
public class DrugSuitDiseaseDaoImpl extends BaseDaoImpl<DrugSuitDisease, Long>
		implements DrugSuitDiseaseDao {

	@Resource
	public SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<DrugSuitDisease> queryByDiseaseCode(String diseaseCode) {
		StringBuilder hql = new StringBuilder();
		hql.append("from DrugSuitDisease where diseaseIcd10 in (:ids)");
		String[] diss = diseaseCode.split(",");
		List<String> idsList = new ArrayList<String>();
		for (String dis : diss) {
			idsList.add(dis);
		}
		return this.getSession().createQuery(hql.toString())
				.setParameterList("ids", idsList).list();
	}

}
