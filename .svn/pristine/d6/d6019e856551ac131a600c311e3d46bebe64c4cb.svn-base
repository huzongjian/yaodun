package com.springmvc.relationdrug.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.FeedBackDao;
import com.springmvc.relationdrug.domain.FeedBack;
import com.springmvc.relationdrug.service.FeedBackService;
@Service
public class FeedBackServiceImpl extends BaseServiceImpl<FeedBack, Long> implements FeedBackService{

	
	@Resource
	@Autowired
	private FeedBackDao feedBackDao;
	@Override
	public BaseDao<FeedBack, Long> getDao() {
		// TODO Auto-generated method stub
		return feedBackDao;
	}

}
