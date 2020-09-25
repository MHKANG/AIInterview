package com.ssafy.ai.model.dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ai.model.dto.Videos;

@Repository
public class VideosDaoImpl implements VideosDao {

	private static final String ns = "com.ssafy.api.Videos.";

	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<Videos> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public Videos select(int video_pk) {
		return template.selectOne(ns + "select", video_pk);
	}

	@Override
	public int insert(Videos v) {
		return template.insert(ns + "insert", v);
	}

	@Override
	public int delete(int video_pk) {
		return template.delete(ns + "delete", video_pk);
	}

	@Override
	public int update(Videos v) {
		return template.update(ns + "update", v);
	}

}
