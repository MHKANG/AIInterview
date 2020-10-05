package com.ssafy.ai.model.dao;

import java.util.List;

import com.ssafy.ai.model.dto.User;

public interface UserDao {

	public List<User> selectAll();
	
	public User select(int user_pk);
	
	public int insert(User u);
	
	public int delete(int user_pk);
	
	public int update(User u);
	
	public User selectByUid(String uid);
	
	public User selectByNickname(String nickname);
	
	public String checkEmail(String uid);
}
