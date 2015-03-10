package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.RepeatDrugLimit;

/**
 * 重复用药
 * 
 * @since 2013-8-6
 */
public interface RepeatDrugLimitDao extends BaseDao<RepeatDrugLimit, Long> {

	/**
	 * 根据限制分类查询重复用药信息
	 * 
	 * @since 2013-8-6
	 * @param level
	 * @return
	 */
	List<RepeatDrugLimit> findByLevel(String level);
}
