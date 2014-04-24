package com.googlecode.goodsamples.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Composite {
	private DataSource one;
	private DataSource two;

	@Autowired
	public Composite(@Qualifier("oneDataSource") DataSource one, @Qualifier("twoDataSource") DataSource two) {
		this.one = one;
		this.two = two;
	}

	public DataSource one() {
		return one;
	}

	public DataSource two() {
		return two;
	}
}
