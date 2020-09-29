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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ai.model.dto.User;
import com.ssafy.ai.model.service.UserService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService uService;

	@ApiOperation(value = "모든 User의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<User>> selectAll() throws Exception {
		logger.debug("User / selectAll - 호출");
		List<User> all = uService.selectAll();
		return new ResponseEntity<List<User>>(all, HttpStatus.OK);
	}

	@ApiOperation(value = "User의 이메일을 반환한다.", response = List.class)
	@GetMapping("/emailCheck")
	public ResponseEntity<Boolean> checkEmail(@RequestParam String email) throws Exception {
		logger.debug("User / checkEmail - 호출");
		boolean is_existed = false;
		String result = uService.checkEmail(email);
		if (result != null) {
			is_existed = true;
		}
		return new ResponseEntity<Boolean>(is_existed, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 User의 정보를 반환한다.", response = User.class)
	@GetMapping("/select/{user_pk}")
	public ResponseEntity<User> select(@PathVariable int user_pk) throws Exception {
		logger.debug("User / select - 호출");

		return new ResponseEntity<User>(uService.select(user_pk), HttpStatus.OK);
	}

	@ApiOperation(value = "login 한다", response = String.class)
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody User u) throws Exception {
		logger.debug("User / login - 호출");
		String userid = u.getUid();
		String password = u.getPassword();

		User tmp = uService.selectByUid(userid);
		if (userid.equals(tmp.getUid()) && password.equals(tmp.getPassword())) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "User의 정보를 삽입한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody User u) {
		logger.debug("User / insert - 호출");
		if (uService.insert(u) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "User 정보를 수정한다", response = String.class)
	@PutMapping()
	public ResponseEntity<String> update(@RequestBody User u) {
		logger.debug("User / update - 호출");

		if (uService.update(u) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "User 삭제한다", response = String.class)
	@DeleteMapping("{user_pk}")
	public ResponseEntity<String> delete(@PathVariable int user_pk) {
		logger.debug("User / delete - 호출");

		if (uService.delete(user_pk) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
