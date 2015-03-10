package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.ConflictFromDrugsDao;
import com.springmvc.relationdrug.domain.ConflictFromDrugs;
import com.springmvc.relationdrug.domain.Dosage;

@Transactional
@Repository
public class ConflictFromDrugsDaoImpl extends
		BaseDaoImpl<ConflictFromDrugs, Long> implements ConflictFromDrugsDao {

	@Resource
	public SessionFactory sessionFactory;

	public List<ConflictFromDrugs> findbytabooid(List<Long> tabooid) {
		Session session = sessionFactory.getCurrentSession();
		System.err.println(tabooid);
		Query query = session
				.createQuery(" from ConflictFromDrugs  where compatTaboo1.id in (:tabooid)");
		query.setParameterList("tabooid", tabooid);
		List<ConflictFromDrugs> d = query.list();
		if (d.isEmpty()) {
			System.err.println("1234");
			return null;
		} else {
			return d;
		}
	}

}
