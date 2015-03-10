package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.OwnKnowledge;
import com.springmvc.relationdrug.domain.User;

public interface OwnKnowledgeDao extends BaseDao<OwnKnowledge, Long> {

	public void cancelAttentiondoctor(User user, Knowledge knowledge);

	public List<OwnKnowledge> findbyUser(User user,Integer page,Integer pagesize);
}