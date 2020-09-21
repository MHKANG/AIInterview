package com.ssafy.ai.model.dao;

import java.util.List;

import com.ssafy.ai.model.dto.Articles;

public interface ArticlesDao {

	public List<Articles> selectAll();
	
	public Articles select(int article_pk);
	
	public int insert(Articles a);
	
	public int delete(int article_pk);
	
	public int update(Articles a);
}
