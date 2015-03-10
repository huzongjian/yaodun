package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.OwnKnowledgeDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.OwnKnowledge;
import com.springmvc.relationdrug.domain.User;

@Transactional
@Repository
public class OwnKnowledgeDaoImpl extends BaseDaoImpl<OwnKnowledge, Long> implements OwnKnowledgeDao {
	
	@Resource
	public SessionFactory sessionFactory;
	

	
	public void cancelAttentiondoctor(User user, Knowledge knowledge) {
		StringBuilder hql = new StringBuilder();
		hql.append("delete OwnKnowledge u where u.user =:user and u.knowledge =:knowledge ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("user", user).setParameter("knowledge", knowledge);
		query.executeUpdate();
		
	}



	public List<OwnKnowledge> findbyUser(User user,Integer page,Integer pagesize) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OwnKnowledge u where u.user =:user");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("user", user);
		int firstResult = (page - 1) * pagesize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pagesize);
		return query.list();
	}
}
