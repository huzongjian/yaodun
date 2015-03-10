package com.springmvc.relationdrug.dao;

import java.util.List;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.relationdrug.domain.User;

public interface UserDao extends BaseDao<User, Long> {
	public User login(User user);
	public String addUser(User user);
	public List<User> findDoctor(Integer page,Integer pagesize);
	public List<User> findAllDoctor();
}