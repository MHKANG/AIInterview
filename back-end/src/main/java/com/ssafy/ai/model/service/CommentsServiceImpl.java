package com.ssafy.ai.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.model.dao.CommentsDao;
import com.ssafy.ai.model.dto.Comments;

@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	CommentsDao cDao;
	
	@Override
	public List<Comments> selectAll() {
		return cDao.selectAll();
	}

	@Override
	public Comments select(int comment_pk) {
		return cDao.select(comment_pk);
	}

	@Override
	public int insert(Comments c) {
		return cDao.insert(c);
	}

	@Override
	public int delete(int comment_pk) {
		return cDao.delete(comment_pk);
	}

	@Override
	public int update(Comments c) {
		return cDao.update(c);
	}

}
