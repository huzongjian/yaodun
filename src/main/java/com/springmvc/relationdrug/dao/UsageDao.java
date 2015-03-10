package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Usage;

/*
 * 用法
 */
public interface UsageDao extends BaseDao<Usage, Long> {
	public List<Usage> findByNameAndSymbol(String name);

	public Usage findByName(String name);
}
