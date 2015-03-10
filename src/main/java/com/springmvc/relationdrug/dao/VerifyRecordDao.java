package com.springmvc.relationdrug.dao;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.VerifyRecordDetail;

public interface VerifyRecordDao extends BaseDao<VerifyRecordDetail, Long> {
	void insertVerifyRecordDetail(VerifyRecordDetail detail);
}
