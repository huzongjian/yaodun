package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.UserQuestionReplyDao;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.domain.UserQuestionReply;
import com.springmvc.relationdrug.service.UserQuestionReplyService;
@Transactional
@Service("userQuestionReplyService")
public class UserQuestionReplyServiceImpl extends BaseServiceImpl<UserQuestionReply, Long> implements UserQuestionReplyService{
    
	@Resource
	private UserQuestionReplyDao userQuestionReplyDao;

	@Override
	public BaseDao<UserQuestionReply, Long> getDao() {
		return userQuestionReplyDao;
	}

	public List<UserQuestionReply> findbyUserQuestion(UserQuestion userQuestion,Integer page,Integer pagesize) {
		// TODO Auto-generated method stub
		return userQuestionReplyDao.findbyUserQuestion(userQuestion,page,pagesize);
	}

	
	
	
}
