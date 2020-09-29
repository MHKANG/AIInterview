package com.ssafy.ai.model.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.model.dao.UserDao;
import com.ssafy.ai.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao uDao;
	
	@Override
	public List<User> selectAll() {
		return uDao.selectAll();
	}

	@Override
	public User select(int user_pk) {
		return uDao.select(user_pk);
	}

	@Override
	public int insert(User u) {
		return uDao.insert(u);
	}

	@Override
	public int delete(int user_pk) {
		return uDao.delete(user_pk);
	}

	@Override
	public int update(User u) {
		return uDao.update(u);
	}

	@Override
	public User selectByUid(String uid) {
		return uDao.selectByUid(uid);
	}
	
	@Override
	 public String checkEmail(String uid) {
	     return uDao.checkEmail(uid);
	 }

}
