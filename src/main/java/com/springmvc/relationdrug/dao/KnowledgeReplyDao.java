package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Knowledge;
import com.springmvc.relationdrug.domain.KnowledgeReply;

public interface KnowledgeReplyDao extends BaseDao<KnowledgeReply, Long> {
	public List<KnowledgeReply> findbyKnowledgeId(Knowledge knowledge);
}