package com.ssafy.ai.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ai.model.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final String ns = "com.ssafy.api.User.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<User> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public User select(int user_pk) {
		return template.selectOne(ns + "select", user_pk);
	}

	@Override
	public int insert(User u) {
		return template.insert(ns + "insert", u);
	}

	@Override
	public int delete(int user_pk) {
		return template.delete(ns + "delete", user_pk);
	}

	@Override
	public int update(User u) {
		return template.update(ns + "update", u);
	}

	@Override
	public User selectByUid(String uid) {
		return template.selectOne(uid);
	}

	@Override
	public String checkEmail(String uid) {
		return template.selectOne(ns + "checkEmail", uid);
	}

}
