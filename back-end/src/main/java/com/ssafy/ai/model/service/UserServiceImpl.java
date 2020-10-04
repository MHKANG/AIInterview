package com.ssafy.ai.model.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.config.BCrpytImpl;
import com.ssafy.ai.model.dao.UserDao;
import com.ssafy.ai.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao uDao;
	
	@Autowired
	BCrpytImpl bcrpytimpl;
	
	@Override
	public List<User> selectAll() {
		return uDao.selectAll();
	}

	@Override
	public User select(int user_pk) {
		return uDao.select(user_pk);
	}

	@Override
	public int SignUp(User u) {
		String encrypted = bcrpytimpl.encrypt(u.getPassword());
		u.setPassword(encrypted);
		
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
	public User selectByNickname(String nickname) {
		return uDao.selectByNickname(nickname);
	}
	
	@Override
	public Object login(User u) {
		Object res = null;
		User check = uDao.selectByUid(u.getUid());
		if(check !=null) {
			if(bcrpytimpl.isMatch(u.getPassword(), check.getPassword())){
				res = check;
			}else {
				res ="password";
			}
		}else {
			res ="email";
		}
		
		return res;
	}

}
