package com.springmvc.relationdrug.service;

import java.util.List;

import com.springmvc.base.service.BaseService;
import com.springmvc.relationdrug.domain.UserQuestion;
import com.springmvc.relationdrug.domain.UserQuestionReply;

public interface UserQuestionReplyService extends BaseService<UserQuestionReply, Long>{

	public List<UserQuestionReply> findbyUserQuestion(UserQuestion userQuestion,Integer page,Integer pagesize);

}
