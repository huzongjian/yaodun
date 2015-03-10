package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.domain.UserQuestion;

public interface UserQuestionDao extends BaseDao<UserQuestion, Long> {

	public List<UserQuestion> findbyUser(User user,Integer page,Integer pagesize);
}