package com.springmvc.relationdrug.service;

import java.util.List;

import com.springmvc.base.service.BaseService;
import com.springmvc.relationdrug.domain.User;

public interface UserService extends BaseService<User, Long>{
	public User login(User user);
	public String addUser(User user);
	public List<User> findDoctor(Integer page,Integer pagesize);
	public List<User> findAllDoctor();
}
