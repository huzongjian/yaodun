package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.KnowledgeDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.User;

@Transactional
@Repository
public class KnowledgeDaoImpl extends BaseDaoImpl<Knowledge, Long> implements KnowledgeDao {

	@Resource
	public SessionFactory sessionFactory;
	
	public List<Knowledge> findByType(String knowledgeTpye,Integer page,Integer pagesize) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Knowledge k where k.type =:type");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("type", knowledgeTpye);
		int firstResult = (page - 1) * pagesize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	
}
