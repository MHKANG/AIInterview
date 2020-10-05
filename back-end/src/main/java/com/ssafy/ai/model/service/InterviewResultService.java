package com.ssafy.ai.model.service;

import java.util.List;

import com.ssafy.ai.model.dto.InterviewResult;

public interface InterviewResultService {

	public List<InterviewResult> selectAll();

	public InterviewResult select(int ir_id);

	public int insert(InterviewResult ir);

	public int delete(int ir_id);

	public int update(InterviewResult ir);
	
	public List<InterviewResult> selectByUsername(String username);
}
