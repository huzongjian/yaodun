package com.springmvc.relationdrug.service;

import java.util.List;

import com.springmvc.base.service.BaseService;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.domain.UserQuestion;

public interface UserQuestionService extends BaseService<UserQuestion, Long>{

   public 	List<UserQuestion> findbyUser(User user,Integer page,Integer pagesize);

}
