package com.ssafy.ai.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ai.model.dto.Comments;

@Repository
public class CommentsDaoImpl implements CommentsDao {

	private static final String ns = "com.ssafy.api.Comments.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Comments> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Comments select(int comment_pk) {
		return template.selectOne(ns + "select", comment_pk);
	}

	@Override
	public int insert(Comments c) {
		return template.insert(ns + "insert", c);
	}

	@Override
	public int delete(int comment_pk) {
		return template.delete(ns + "delete", comment_pk);
	}

	@Override
	public int update(Comments c) {
		return template.update(ns + "update", c);
	}

}
