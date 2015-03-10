package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.OwnDoctorDao;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.service.OwnDoctorService;
@Transactional
@Service("ownDoctorService")
public class OwnDoctorServiceImpl extends BaseServiceImpl<OwnDoctor, Long> implements OwnDoctorService{
    
	@Resource
	private OwnDoctorDao ownDoctorDao;

	@Override
	public BaseDao<OwnDoctor, Long> getDao() {
		return ownDoctorDao;
	}

	public void cancelAttentiondoctor(User user, User doctor) {
		 ownDoctorDao.cancelAttentiondoctor(user, doctor);
		
	}

	public List<OwnDoctor> findbyUser(User user,Integer page,Integer pagesize) {
		
		return   ownDoctorDao.findbyUser( user,page,pagesize);
	}

	
	
	
}
