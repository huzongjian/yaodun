package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Knowledge;

public interface KnowledgeDao extends BaseDao<Knowledge, Long> {

	public List<Knowledge> findByType(String knowledgeTpye,Integer page,Integer pagesize);
}