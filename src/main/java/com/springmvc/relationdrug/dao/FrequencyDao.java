package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Frequency;

/*
 * 用法
 */
public interface FrequencyDao extends BaseDao<Frequency, Long> {
	public List<Frequency> findByNameAndSymbol(String name);

	public Frequency findByName(String name);
}
