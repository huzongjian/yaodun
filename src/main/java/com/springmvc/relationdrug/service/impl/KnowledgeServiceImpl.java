package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.KnowledgeDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.service.KnowledgeService;
@Transactional
@Service("KnowledgeService")
public class KnowledgeServiceImpl extends BaseServiceImpl<Knowledge, Long> implements KnowledgeService{
    
	@Resource
	private KnowledgeDao knowledgeDao;

	@Override
	public BaseDao<Knowledge, Long> getDao() {
		return knowledgeDao;
	}

	public List<Knowledge> findByType(String knowledgeTpye,Integer page,Integer pagesize) {
		return knowledgeDao.findByType(knowledgeTpye,page,pagesize);
	}

	
	
	
}
