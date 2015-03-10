package com.springmvc.relationdrug.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.impl.LotteryResultDaoImpl;
import com.springmvc.relationdrug.domain.LotteryResult;
import com.springmvc.relationdrug.service.LotteryResultService;

@Service
public class LotteryResultServiceImpl extends BaseServiceImpl<LotteryResult, Long> implements LotteryResultService{

	@Resource
	public LotteryResultDaoImpl lotteryResultDao;
	
	@Override
	public BaseDao<LotteryResult, Long> getDao() {
		return lotteryResultDao;
	}

}
