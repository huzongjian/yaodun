package com.springmvc.relationdrug.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.base.dao.BaseDao;
import com.springmvc.base.service.impl.BaseServiceImpl;
import com.springmvc.relationdrug.dao.impl.UserDaoImpl;
import com.springmvc.relationdrug.domain.User;
import com.springmvc.relationdrug.service.UserService;
@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{
    
	@Resource
	private UserDaoImpl userDao;

	@Override
	public BaseDao<User, Long> getDao() {
		return userDao;
	}

	public User login(User user) {
		return userDao.login(user);
	}
   public String addUser(User user){
	   return userDao.addUser(user);
   }
	public List<User> findDoctor(Integer page,Integer pagesize){
		return userDao.findDoctor(page,pagesize);
	}
	public List<User> findAllDoctor(){
		return userDao.findAllDoctor();
	}
}
