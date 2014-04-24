package com.googlecode.goodsamples.lab;

public class Visitor {
	private String id;
	private String visitorId;
	private String nickname;

	public Visitor(String id, String visitorId, String nickname) {
		this.id = id;
		this.visitorId = visitorId;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}
	
	public String getVisitorId() {
		return visitorId;
	}

	public String getNickname() {
		return nickname;
	}
}
