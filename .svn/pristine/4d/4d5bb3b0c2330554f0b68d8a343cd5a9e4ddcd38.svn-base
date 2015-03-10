package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.OwnDoctorDao;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.User;

@Transactional
@Repository
public class OwnDoctorDaoImpl extends BaseDaoImpl<OwnDoctor, Long> implements OwnDoctorDao {
	
	@Resource
	public SessionFactory sessionFactory;
	
	public void cancelAttentiondoctor(User user, User doctor) {
		StringBuilder hql = new StringBuilder();
		hql.append("delete OwnDoctor u where u.user =:user and u.doctor =:doctor ");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("user", user).setParameter("doctor", doctor);
		query.executeUpdate();
		
	}
	public List<OwnDoctor> findbyUser(User user,Integer page,Integer pagesize) {
		StringBuilder hql = new StringBuilder();
		hql.append("from OwnDoctor u where u.user =:user");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("user", user);
		int firstResult = (page - 1) * pagesize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pagesize);
		return query.list();
	}
	
	
}
