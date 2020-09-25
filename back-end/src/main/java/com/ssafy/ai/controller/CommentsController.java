package com.ssafy.ai.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ai.model.dto.Comments;
import com.ssafy.ai.model.service.CommentsService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentsService cService;

	@ApiOperation(value = "모든 Comments의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Comments>> selectAll() throws Exception {
		logger.debug("Comments / selectAll - 호출");
		List<Comments> all = cService.selectAll();
		return new ResponseEntity<List<Comments>>(all, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 Comments의 정보를 반환한다.", response = Comments.class)
	@GetMapping("/select/{comment_pk}")
	public ResponseEntity<Comments> select(@PathVariable int comment_pk) throws Exception {
		logger.debug("Comments / select - 호출");

		return new ResponseEntity<Comments>(cService.select(comment_pk), HttpStatus.OK);
	}

	@ApiOperation(value = "Comments의 정보를 삽입한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Comments c) {
		logger.debug("Comments / insert - 호출");
		if (cService.insert(c) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Articles 정보를 수정한다", response = String.class)
	@PutMapping()
	public ResponseEntity<String> update(@RequestBody Comments c) {
		logger.debug("Comments / update - 호출");

		if (cService.update(c) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Comments 삭제한다", response = String.class)
	@DeleteMapping("{comment_pk}")
	public ResponseEntity<String> delete(@PathVariable int comment_pk) {
		logger.debug("Comments / delete - 호출");

		if (cService.delete(comment_pk) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
