package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.OwnDoctor;
import com.springmvc.relationdrug.domain.User;

public interface OwnDoctorDao extends BaseDao<OwnDoctor, Long> {
	public void cancelAttentiondoctor(User user,User doctor);

	public List<OwnDoctor> findbyUser(User user,Integer page,Integer pagesize);
}