package com.googlecode.goodsamples.lab;

public class Profile {
	private String id;
	private String name;
	private int age;
	private String hobby;
	
	public Profile(String id, String name, int age, String hobby) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getHobby() {
		return hobby;
	}
}
