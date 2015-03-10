package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.UserQuestionDao;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.service.UserQuestionService;
@Transactional
@Service("userQuestionService")
public class UserQuestionServiceImpl extends BaseServiceImpl<UserQuestion, Long> implements UserQuestionService{
    
	@Resource
	private UserQuestionDao userQuestionDao;

	@Override
	public BaseDao<UserQuestion, Long> getDao() {
		return userQuestionDao;
	}

	public List<UserQuestion> findbyUser(User user,Integer page,Integer pagesize) {
		// TODO Auto-generated method stub
		return userQuestionDao.findbyUser(user,page,pagesize);
	}

	
	
	
}
