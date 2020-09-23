package com.ssafy.ai.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.model.dao.VideosDao;
import com.ssafy.ai.model.dto.Videos;

@Service
public class VideosServiceImpl implements VideosService {

	@Autowired
	VideosDao vDao;

	@Override
	public List<Videos> selectAll() {
		return vDao.selectAll();
	}

	@Override
	public Videos select(int video_pk) {
		return vDao.select(video_pk);
	}

	@Override
	public int insert(Videos v) {
		return vDao.insert(v);
	}

	@Override
	public int delete(int video_pk) {
		return vDao.delete(video_pk);
	}

	@Override
	public int update(Videos v) {
		return vDao.update(v);
	}

}
