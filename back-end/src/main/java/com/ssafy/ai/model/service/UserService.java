package com.ssafy.ai.model.service;

import java.util.List;

import com.ssafy.ai.model.dto.User;

public interface UserService {

	public List<User> selectAll();
	
	public Object login(User u);

	public User select(int user_pk);

	public int SignUp(User u);

	public int delete(int user_pk);

	public int update(User u);
	
	public User selectByUid(String uid);
	
	public User selectByNickname(String nickname);
}
