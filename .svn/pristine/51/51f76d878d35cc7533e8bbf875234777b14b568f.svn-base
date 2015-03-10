package com.springmvc.relationdrug.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.FeedBackDao;
import com.springmvc.relationdrug.domain.FeedBack;
@Transactional
@Repository
public class FeedBackDaoImpl extends BaseDaoImpl<FeedBack, Long> implements FeedBackDao{
	@Resource
	public SessionFactory sessionFactory;
}
