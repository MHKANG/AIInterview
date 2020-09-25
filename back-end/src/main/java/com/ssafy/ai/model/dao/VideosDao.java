package com.ssafy.ai.model.dao;

import java.util.List;

import com.ssafy.ai.model.dto.Videos;

public interface VideosDao {

	public List<Videos> selectAll();

	public Videos select(int video_pk);

	public int insert(Videos v);

	public int delete(int video_pk);

	public int update(Videos v);
}
