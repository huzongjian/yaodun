package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.KnowledgeReplyDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.KnowledgeReply;
import com.springmvc.relationdrug.service.KnowledgeReplyService;
@Transactional
@Service("knowledgeReplyService")
public class KnowledgeReplyServiceImpl extends BaseServiceImpl<KnowledgeReply, Long> implements KnowledgeReplyService{
    
	@Resource
	private KnowledgeReplyDao knowledgeReplyDao;

	@Override
	public BaseDao<KnowledgeReply, Long> getDao() {
		return knowledgeReplyDao;
	}

	public List<KnowledgeReply> findbyKnowledgeId(Knowledge knowledge) {
		// TODO Auto-generated method stub
		return knowledgeReplyDao.findbyKnowledgeId(knowledge);
	}

	
	
	
}
