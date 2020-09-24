package com.ssafy.ai.model.dto;

import java.util.Date;

public class Articles {
	private int article_pk;
	private int user_pk;
	private String title;
	private String content;
	private String writer;
	private int view;
	private String file;
	private Date create_date;
	private Date change_date;

	public int getArticle_pk() {
		return article_pk;
	}

	public void setArticle_pk(int article_pk) {
		this.article_pk = article_pk;
	}

	public int getUser_pk() {
		return user_pk;
	}

	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getChange_date() {
		return change_date;
	}

	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

	public Articles(int article_pk, int user_pk, String title, String content, String writer, int view, String file,
			Date create_date, Date change_date) {
		super();
		this.article_pk = article_pk;
		this.user_pk = user_pk;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.view = view;
		this.file = file;
		this.create_date = create_date;
		this.change_date = change_date;
	}
}
