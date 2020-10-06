package com.ssafy.ai.model.dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ai.model.dto.InterviewResult;

@Repository
public class InterviewResultDaoImpl implements InterviewResultDao{

	private static final String ns = "com.ssafy.api.InterviewResult.";
	
	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<InterviewResult> selectAll() {
		return template.selectList(ns + "selectAll");
	}

	@Override
	public InterviewResult select(int ir_id) {
		return template.selectOne(ns + "select", ir_id);
	}
	@Override
	public List<InterviewResult> selectByUsername(String username) {
		List<InterviewResult> temp = template.selectList(ns +"selectByUsername", username);
		System.out.println("temp :" +temp.toString());
		System.out.println(username);
		return template.selectList(ns +"selectByUsername", username);
	}

	@Override
	public int insert(InterviewResult ir) {
		return template.insert(ns + "insert", ir);
	}

	@Override
	public int delete(int ir_id) {
		return template.delete(ns + "delete", ir_id);
	}

	@Override
	public int update(InterviewResult ir) {
		return template.update(ns + "update", ir);
	}
	
}