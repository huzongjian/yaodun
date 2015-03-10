package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.domain.UserQuestionReply;

public interface UserQuestionReplyDao extends BaseDao<UserQuestionReply, Long> {
	public List<UserQuestionReply> findbyUserQuestion(UserQuestion userQuestion,Integer page,Integer pagesize);
}