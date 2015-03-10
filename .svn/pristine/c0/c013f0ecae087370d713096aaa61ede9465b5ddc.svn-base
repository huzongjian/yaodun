package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.OwnKnowledgeDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.OwnKnowledge;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.service.OwnKnowledgeService;
@Transactional
@Service("ownKnowledgeService")
public class OwnKnowledgeServiceImpl extends BaseServiceImpl<OwnKnowledge, Long> implements OwnKnowledgeService{
    
	@Resource
	private OwnKnowledgeDao ownKnowledgeDao;

	@Override
	public BaseDao<OwnKnowledge, Long> getDao() {
		return ownKnowledgeDao;
	}

	public void cancelAttentiondoctor(User user, Knowledge knowledge) {
		ownKnowledgeDao.cancelAttentiondoctor(user,knowledge);
		
	}

	public List<OwnKnowledge> findbyUser(User user,Integer page,Integer pagesize) {
		return ownKnowledgeDao.findbyUser(user,page,pagesize);
	}

	
	
	
}
