package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.UserQuestionDao;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.domain.UserQuestion;

@Transactional
@Repository
public class UserQestionDaoImpl extends BaseDaoImpl<UserQuestion, Long> implements UserQuestionDao {
	@Resource
	public SessionFactory sessionFactory;
	public List<UserQuestion> findbyUser(User user,Integer page,Integer pagesize) {
		// TODO Auto-generated method stub
			StringBuilder hql = new StringBuilder();
			hql.append("from UserQuestion u where u.user =:user");
			Query query = sessionFactory.getCurrentSession().createQuery(
					hql.toString());
			query.setParameter("user", user);
			int firstResult = (page - 1) * pagesize;
			query.setFirstResult(firstResult);
			query.setMaxResults(pagesize);
			return query.list();
		
	}
	
	
}
