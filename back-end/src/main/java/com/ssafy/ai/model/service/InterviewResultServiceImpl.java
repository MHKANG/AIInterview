package com.ssafy.ai.model.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ai.model.dao.InterviewResultDao;
import com.ssafy.ai.model.dto.InterviewResult;

@Service
public class InterviewResultServiceImpl implements InterviewResultService{

	@Autowired
	InterviewResultDao irDao;
	
	@Override
	public List<InterviewResult> selectAll() {
		return irDao.selectAll();
	}

	@Override
	public InterviewResult select(int ir_id) {
		return irDao.select(ir_id);
	}

	@Override
	public int insert(InterviewResult ir) {
		return irDao.insert(ir);
	}

	@Override
	public int delete(int ir_id) {
		return irDao.delete(ir_id);
	}

	@Override
	public int update(InterviewResult ir) {
		return irDao.update(ir);
	}

}
