package com.ssafy.ai.model.dto;

import java.util.Date;

public class Videos {
	private int video_pk;
	private int user_pk;
	private String filename;
	private Date create_date;
	private int video_length;
	private String video_url;

	public int getVideo_pk() {
		return video_pk;
	}

	public void setVideo_pk(int video_pk) {
		this.video_pk = video_pk;
	}

	public int getUser_pk() {
		return user_pk;
	}

	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getVideo_length() {
		return video_length;
	}

	public void setVideo_length(int video_length) {
		this.video_length = video_length;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public Videos(int video_pk, int user_pk, String filename, Date create_date, int video_length, String video_url) {
		super();
		this.video_pk = video_pk;
		this.user_pk = user_pk;
		this.filename = filename;
		this.create_date = create_date;
		this.video_length = video_length;
		this.video_url = video_url;
	}

}
