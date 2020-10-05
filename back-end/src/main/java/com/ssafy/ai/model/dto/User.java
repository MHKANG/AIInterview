package com.ssafy.ai.model.dto;

import java.sql.Date;

public class User {
	private int user_pk;
	private String uid;
	private String password;
	private String nickname;
	private String username;
	private boolean is_admin;
	private boolean is_activated;
	private Date auth_date;
	private String gender;
	private int age;
	
	public int getUser_pk() {
		return user_pk;
	}
	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public boolean isIs_activated() {
		return is_activated;
	}
	public void setIs_activated(boolean is_activated) {
		this.is_activated = is_activated;
	}
	public Date getAuth_date() {
		return auth_date;
	}
	public void setAuth_date(Date auth_date) {
		this.auth_date = auth_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public User(String uid, String password) {
		this.uid = uid;
		this.password = password;
	}
	
	public User(int user_pk, String uid, String password, String nickname, String username, boolean is_admin,
			boolean is_activated, Date auth_date, String gender, int age) {
		super();
		this.user_pk = user_pk;
		this.uid = uid;
		this.password = password;
		this.nickname = nickname;
		this.username = username;
		this.is_admin = is_admin;
		this.is_activated = is_activated;
		this.auth_date = auth_date;
		this.gender = gender;
		this.age = age;
	}	
	
	
	
	@Override
	public String toString() {
		return "User [user_pk=" + user_pk + ", uid=" + uid + ", password=" + password + ", nickname=" + nickname
				+ ", username=" + username + ", is_admin=" + is_admin + ", is_activated=" + is_activated
				+ ", auth_date=" + auth_date + ", gender=" + gender + ", age=" + age + "]";
	}
	public User() {}
	
}
