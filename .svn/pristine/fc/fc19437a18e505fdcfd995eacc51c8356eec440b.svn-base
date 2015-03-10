package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.KnowledgeReplyDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.KnowledgeReply;
import com.springmvc.relationdrug.domain.UserQuestionReply;

@Transactional
@Repository
public class KnowledgeReplyDaoImpl extends BaseDaoImpl<KnowledgeReply, Long> implements KnowledgeReplyDao {
   @Resource
	public SessionFactory sessionFactory;
	public List<KnowledgeReply> findbyKnowledgeId(Knowledge knowledge) {
		// TODO Auto-generated method stub
					StringBuilder hql = new StringBuilder();
					
					hql.append("from KnowledgeReply u where u.knowledge =:knowledge");
					Query query = sessionFactory.getCurrentSession().createQuery(
							hql.toString());
					query.setParameter("knowledge", knowledge);
					return query.list();
	}
	
	
}
