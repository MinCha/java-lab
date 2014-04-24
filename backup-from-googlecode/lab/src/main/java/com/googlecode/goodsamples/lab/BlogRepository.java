package com.googlecode.goodsamples.lab;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BlogRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Visitor> findAllVisitorsBy(String id) {
		final List<Visitor> result = new ArrayList<Visitor>();

		jdbcTemplate.query("SELECT * FROM visitor WHERE id ='" + id + "'", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Visitor(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		});

		return result;
	}

	public List<Article> findAllArticlesBy(String id) {
		final List<Article> result = new ArrayList<Article>();

		jdbcTemplate.query("SELECT * FROM article WHERE id ='" + id + "'", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Article(rs.getString(1), rs.getString(2), rs.getString(3), rs
						.getDate(4), rs.getInt(5)));
			}
		});

		return result;
	}

	public Profile findProfileBy(String id) {
		return jdbcTemplate.queryForObject("SELECT * FROM profile WHERE id ='" + id + "'", new RowMapper<Profile>() {
			@Override
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Profile(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
			}
		});
	}

	public List<Comment> findCommentsBy(String id) {
		final List<Comment> result = new ArrayList<Comment>();

		jdbcTemplate.query("SELECT * FROM comment WHERE id ='" + id + "'", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				result.add(new Comment(rs.getString(1), rs.getString(2), rs.getDate(3)));
			}
		});

		return result;
	}
}
