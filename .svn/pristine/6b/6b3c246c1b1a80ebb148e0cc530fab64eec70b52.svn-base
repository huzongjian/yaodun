package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.UsageDao;
import com.springmvc.relationdrug.domain.Usage;

/**
 * Summary : 用法
 * 
 * @since 2013-6-19
 */
@Transactional
@Repository
public class UsageDaoImpl extends BaseDaoImpl<Usage, Long> implements UsageDao {

	public Usage get(long id) {
		return (Usage) this.getSession().get(Usage.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usage> findAll() {
		String hql = "from Usage order by id";
		return this.getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<Usage> findByNameAndSymbol(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Usage where (name like ? or upper(symbol) like ?)");
		return this.getSession().createQuery(hql.toString())
				.setString(0, "%" + name + "%")
				.setString(1, "%" + name.toUpperCase() + "%").setMaxResults(20)
				.list();
	}

	public Usage findByName(String name) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Usage where name = ? ");
		return (Usage) this.getSession().createQuery(hql.toString())
				.setString(0, name).uniqueResult();

	}

}
