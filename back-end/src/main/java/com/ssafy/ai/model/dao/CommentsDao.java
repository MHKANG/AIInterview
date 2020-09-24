package com.ssafy.ai.model.dao;

import java.util.List;

import com.ssafy.ai.model.dto.Comments;

public interface CommentsDao {

	public List<Comments> selectAll();

	public Comments select(int comment_pk);

	public int insert(Comments c);

	public int delete(int comment_pk);

	public int update(Comments c);
}