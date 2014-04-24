package com.googlecode.goodsamples.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Shows an way to rollback two datasources without distrributed transaction.
 * </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class TwoDataSourceRollbackIntegrationTest {
	@Autowired
	JdbcTemplate oneJdbcTemplate;
	@Autowired
	JdbcTemplate twoJdbcTemplate;
	String sqlToCount = "SELECT COUNT(*) FROM sample;";

	@Test
	public void springContextShouldBeLoadedWithoutException() {
		String sqlToInsert = "INSERT INTO sample VALUES (1);";
		oneJdbcTemplate.execute(sqlToInsert);
		twoJdbcTemplate.execute(sqlToInsert);

		assertEquals(oneJdbcTemplate.queryForInt(sqlToCount), 1);
		assertEquals(twoJdbcTemplate.queryForInt(sqlToCount), 1);
	}

	@BeforeTransaction
	public void createSchema() {
		String sqlToCreate = "CREATE TABLE sample (id INT NOT NULL PRIMARY KEY);";
		oneJdbcTemplate.execute(sqlToCreate);
		twoJdbcTemplate.execute(sqlToCreate);
	}

	@AfterTransaction
	public void assertThenDropSchemaAfterRollback() {
		assertThatThereIsNoItem();
		dropSchema();
	}

	private void dropSchema() {
		String sqlToDrop = "DROP TABLE sample;";
		oneJdbcTemplate.execute(sqlToDrop);
		twoJdbcTemplate.execute(sqlToDrop);
	}

	private void assertThatThereIsNoItem() {
		assertEquals(oneJdbcTemplate.queryForInt(sqlToCount), 0);
		assertEquals(twoJdbcTemplate.queryForInt(sqlToCount), 0);
	}
}
