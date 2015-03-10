package com.springmvc.relationdrug.service;

import java.util.List;

import com.springmvc.base.service.BaseService;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.User;

public interface OwnDoctorService extends BaseService<OwnDoctor, Long>{
	
	
	public void cancelAttentiondoctor(User user,User doctor);

	public List<OwnDoctor> findbyUser(User user,Integer page,Integer pagesize);
	

}
