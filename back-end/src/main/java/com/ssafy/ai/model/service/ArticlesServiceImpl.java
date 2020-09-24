package com.ssafy.ai.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.model.dao.ArticlesDao;
import com.ssafy.ai.model.dto.Articles;

@Service
public class ArticlesServiceImpl implements ArticlesService{

	@Autowired
	ArticlesDao aDao;
	
	@Override
	public List<Articles> selectAll() {
		return aDao.selectAll();
	}

	@Override
	public Articles select(int article_pk) {
		return aDao.select(article_pk);
	}

	@Override
	public int insert(Articles a) {
		return aDao.insert(a);
	}

	@Override
	public int delete(int article_pk) {
		return aDao.delete(article_pk);
	}

	@Override
	public int update(Articles a) {
		return aDao.update(a);
	}

}
