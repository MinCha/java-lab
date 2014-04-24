package com.googlecode.goodsamples.spring.support;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dependency {
	@Autowired
	private DataSource oneDataSource;
	@Autowired
	private DataSource twoDataSource;

	public Dependency() {
	}

	public DataSource getOneDataSource() {
		return oneDataSource;
	}

	public DataSource getTwoDataSource() {
		return twoDataSource;
	}
}
