package com.googlecode.goodsamples.lab;

import java.sql.Date;

public class Comment {
	private String id;
	private String comment;
	private Date createdate;

	public Comment(String id, String comment, Date createdate) {
		this.id = id;
		this.comment = comment;
		this.createdate = createdate;
	}
	
	public String getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public Date getCreatedate() {
		return createdate;
	}
}
