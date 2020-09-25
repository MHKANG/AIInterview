package com.ssafy.ai.model.dto;

import java.util.Date;

public class Comments {
	private int comment_pk;
	private int article_pk;
	private int user_pk;
	private String comment;
	private String commentor;
	private Date create_date;

	public int getComment_pk() {
		return comment_pk;
	}

	public void setComment_pk(int comment_pk) {
		this.comment_pk = comment_pk;
	}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentor() {
		return commentor;
	}

	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Comments(int comment_pk, int article_pk, int user_pk, String comment, String commentor, Date create_date) {
		super();
		this.comment_pk = comment_pk;
		this.article_pk = article_pk;
		this.user_pk = user_pk;
		this.comment = comment;
		this.commentor = commentor;
		this.create_date = create_date;
	}

}
