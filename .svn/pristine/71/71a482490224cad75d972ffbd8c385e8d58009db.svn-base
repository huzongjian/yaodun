package com.springmvc.relationdrug.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.UserDao;
import com.springmvc.relationdrug.domain.User;

@Transactional
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
	@Resource
	public SessionFactory sessionFactory;

	public User login(User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("from User u where (u.userName= :username or u.email = :username or u.telephone = :username) and password=:password");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("username", user.getUserName());
		query.setParameter("password", user.getPassword());
		return (User) query.uniqueResult();
	}

	public String addUser(User user) {
		StringBuilder hql = new StringBuilder();
		hql.append("from User u where u.userName=:username or u.email = :username or u.telephone =:username");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		query.setParameter("username", user.getUserName());
		User u =(User) query.uniqueResult();
		if (u==null){
			this.save(user);
			return "success"; 
			
		}else{
			return "failure";
					
					
		}
		
		
	}

	public List<User> findDoctor(Integer page,Integer pagesize) {
		StringBuilder hql = new StringBuilder();
		hql.append("from User u where u.userType is not null and u.userType !=''");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		int firstResult = (page - 1) * pagesize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pagesize);
		return query.list();
		
	}
	
	public List<User> findAllDoctor() {
		StringBuilder hql = new StringBuilder();
		hql.append("from User u where u.userType is not null and u.userType !=''");
		Query query = sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		return query.list();
		
	}
	

}
