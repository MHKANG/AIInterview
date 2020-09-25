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

import com.ssafy.ai.model.dto.Articles;
import com.ssafy.ai.model.service.ArticlesService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

	private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ArticlesService aService;

	@ApiOperation(value = "모든 Articles의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Articles>> selectAll() throws Exception {
		logger.debug("Articles / selectAll - 호출");
		List<Articles> all = aService.selectAll();
		return new ResponseEntity<List<Articles>>(all, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 Articles의 정보를 반환한다.", response = Articles.class)
	@GetMapping("/select/{article_pk}")
	public ResponseEntity<Articles> select(@PathVariable int article_pk) throws Exception {
		logger.debug("Articles / select - 호출");

		return new ResponseEntity<Articles>(aService.select(article_pk), HttpStatus.OK);
	}

	@ApiOperation(value = "Articles의 정보를 삽입한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Articles a) {
		logger.debug("Articles / insert - 호출");
		if (aService.insert(a) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Articles 정보를 수정한다", response = String.class)
	@PutMapping()
	public ResponseEntity<String> update(@RequestBody Articles a) {
		logger.debug("Articles / update - 호출");

		if (aService.update(a) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Articles 삭제한다", response = String.class)
	@DeleteMapping("{article_pk}")
	public ResponseEntity<String> delete(@PathVariable int article_pk) {
		logger.debug("Articles / delete - 호출");

		if (aService.delete(article_pk) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
