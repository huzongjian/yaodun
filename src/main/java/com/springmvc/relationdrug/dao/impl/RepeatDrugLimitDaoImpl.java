package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.RepeatDrugLimitDao;
import com.springmvc.relationdrug.domain.RepeatDrugLimit;

/**
 * 重复用药Dao
 */
@Transactional
@Repository
public class RepeatDrugLimitDaoImpl extends BaseDaoImpl<RepeatDrugLimit, Long>
		implements RepeatDrugLimitDao {

	@SuppressWarnings("unchecked")
	public List<RepeatDrugLimit> findByLevel(String level) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from RepeatDrugLimit t where t.checkLimit =:level");
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("level", level);
		return query.list();
	}

}
