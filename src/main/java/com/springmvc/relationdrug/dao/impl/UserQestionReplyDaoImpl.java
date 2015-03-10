package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.UserQuestionReplyDao;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.domain.UserQuestionReply;

@Transactional
@Repository
public class UserQestionReplyDaoImpl extends BaseDaoImpl<UserQuestionReply, Long> implements UserQuestionReplyDao {
   @Resource
	public SessionFactory sessionFactory;
	public List<UserQuestionReply> findbyUserQuestion(UserQuestion userQuestion,Integer page,Integer pagesize) {
		// TODO Auto-generated method stub
					StringBuilder hql = new StringBuilder();
					hql.append("from UserQuestionReply u where u.userQuestion =:userQuestion");
					Query query = sessionFactory.getCurrentSession().createQuery(
							hql.toString());
					query.setParameter("userQuestion", userQuestion);
					int firstResult = (page - 1) * pagesize;
					query.setFirstResult(firstResult);
					query.setMaxResults(pagesize);
					return query.list();
	}
	
	
}
