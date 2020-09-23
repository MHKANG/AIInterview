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

import com.ssafy.ai.model.dto.Videos;
import com.ssafy.ai.model.service.VideosService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/videos")
public class VideosController {

	private static final Logger logger = LoggerFactory.getLogger(VideosController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private VideosService vService;

	@ApiOperation(value = "모든 Videos의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Videos>> selectAll() throws Exception {
		logger.debug("Videos / selectAll - 호출");
		List<Videos> all = vService.selectAll();
		return new ResponseEntity<List<Videos>>(all, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 Videos의 정보를 반환한다.", response = Videos.class)
	@GetMapping("/select/{video_pk}")
	public ResponseEntity<Videos> select(@PathVariable int video_pk) throws Exception {
		logger.debug("Videos / select - 호출");

		return new ResponseEntity<Videos>(vService.select(video_pk), HttpStatus.OK);
	}

	@ApiOperation(value = "Videos의 정보를 삽입한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Videos v) {
		logger.debug("Videos / insert - 호출");
		if (vService.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Videos 정보를 수정한다", response = String.class)
	@PutMapping()
	public ResponseEntity<String> update(@RequestBody Videos v) {
		logger.debug("Videos / update - 호출");

		if (vService.update(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Videos 삭제한다", response = String.class)
	@DeleteMapping("{video_pk}")
	public ResponseEntity<String> delete(@PathVariable int video_pk) {
		logger.debug("Videos / delete - 호출");

		if (vService.delete(video_pk) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
