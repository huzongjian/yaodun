package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.OtherInfoDao;
import com.springmvc.relationdrug.domain.Otherinfo;
@Transactional
@Repository
public class OtherInfoDaoimpl extends BaseDaoImpl<Otherinfo, Long> implements OtherInfoDao{
	@Resource
	public SessionFactory sessionFactory;

	public List<Otherinfo> findbytype(Integer type) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" from Otherinfo where type = ?");
	 Query query = this.getSession().createQuery(hql.toString()).setInteger(0, type);
	return query.list();
	}
}
