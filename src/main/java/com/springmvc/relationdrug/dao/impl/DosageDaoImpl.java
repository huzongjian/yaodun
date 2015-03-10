package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.DosageDao;
import com.springmvc.relationdrug.domain.Dosage;

/**
 * 用量
 * 
 * @author feifangyuan
 * @since 2013-8-1
 */
@Transactional
@Repository
public class DosageDaoImpl extends BaseDaoImpl<Dosage, Long> implements
		DosageDao {

	@Resource
	public SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Dosage findByDrugIdAndUsage(Long drugId, Long usage) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery(" from Dosage  where basicDrugCheck.id =:basicid and usage.id =:usage");
		query.setParameter("basicid", drugId);
		query.setParameter("usage", usage);
		List<Dosage> d = query.list();
		if (d.isEmpty()) {
			return null;
		} else {
			return d.get(0);
		}

		/*
		 * Criteria c = getSession().createCriteria(Dosage.class);
		 * c.add(Expression.eq("usage.id",
		 * usage)).add(Expression.eq("basicDrugCheck.id", drugId)); List<Dosage>
		 * list = c.list(); if (list.size() >= 1){ Dosage du = list.get(0);
		 * this.getSession().evict(du); return du; } return null;
		 */
	}

	@SuppressWarnings("unchecked")
	public List<Dosage> findByBasicId(Long basicid) {
		Session session = this.getSession();
		Query query = session
				.createQuery(" from Dosage where basicDrugCheck.id =:basicid");
		query.setParameter("basicid", basicid);
		List<Dosage> list = query.list();
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

}
