package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.FrequencyDao;
import com.springmvc.relationdrug.domain.Frequency;

/**
 * Summary : 频次
 * 
 * 
 */
@Transactional
@Repository
public class FrequencyDaoImpl extends BaseDaoImpl<Frequency, Long> implements
		FrequencyDao {

	public Frequency get(long id) {
		return (Frequency) this.getSession().get(Frequency.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Frequency> findAll() {
		String hql = "from Frequency order by id";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<Frequency> findByNameAndSymbol(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Frequency where (name like ? or upper(symbol) like ?)");
		return this.getSession().createQuery(hql.toString()).setMaxResults(30)
				.setString(0, "%" + name + "%")
				.setString(1, "%" + name.toUpperCase() + "%").list();
	}

	public Frequency findByName(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Frequency where name = ?");
		return (Frequency) this.getSession().createQuery(hql.toString())
				.setMaxResults(30).setString(0, name).uniqueResult();

	}

}
