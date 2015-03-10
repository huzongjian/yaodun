package com.springmvc.relationdrug.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.Dosage;

@Transactional
@Repository
public interface DosageDao extends BaseDao<Dosage, Long> {

	/**
	 * 根据药品id和用法获取检测内容
	 * 
	 * @param drugId
	 * @param usage
	 * @return
	 */
	public Dosage findByDrugIdAndUsage(Long drugId, Long usage);

	/**
	 * 根据药品基表id查询用法
	 * 
	 * @since 2013-8-5
	 * @param basicid
	 * @return
	 */
	public List<Dosage> findByBasicId(Long basicid);
}
