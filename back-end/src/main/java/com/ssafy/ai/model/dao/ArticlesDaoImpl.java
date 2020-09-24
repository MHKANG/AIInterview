package com.ssafy.ai.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ai.model.dto.Articles;

@Repository
public class ArticlesDaoImpl implements ArticlesDao{

	private static final String ns = "com.ssafy.api.Articles.";
	
	@Autowired
	SqlSessionTemplate template;
	
	@Override
	public List<Articles> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Articles select(int article_pk) {
		return template.selectOne(ns + "select", article_pk);
	}

	@Override
	public int insert(Articles a) {
		return template.insert(ns + "insert", a);
	}

	@Override
	public int delete(int article_pk) {
		return template.delete(ns + "delete", article_pk);
	}

	@Override
	public int update(Articles a) {
		return template.update(ns + "update", a);
	}

}
