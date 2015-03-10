package com.springmvc.relationdrug.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.impl.BaseDaoImpl;
import com.springmvc.relationdrug.dao.VerifyRecordDao;
import com.springmvc.relationdrug.domain.VerifyRecordDetail;

/**
 * Summary : 检测记录DAO
 * 
 * @since 2013-8-7
 */
@Transactional
@Repository
public class VerifyRecordDaoImpl extends BaseDaoImpl<VerifyRecordDetail, Long>
		implements VerifyRecordDao {

	public void insertVerifyRecordDetail(VerifyRecordDetail detail) {
		this.getSession()
				.save(VerifyRecordDetail.class.getSimpleName(), detail);
	}
}
