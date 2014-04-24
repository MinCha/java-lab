package com.googlecode.goodsamples.gridgain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.gridgain.grid.Grid;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridFactory;
import org.gridgain.grid.GridTaskFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * GridGain 2.1 should be installed before executing this test.
 * </p>
 * @author minslovey
 */
public class GridHelloWorldTaskTest {
	@Test
	public void shouldDisplayHelloOnNodes() throws GridException {
		final String source = "Hello World Hi NHN";
		Grid grid = GridFactory.getGrid();
		
		GridTaskFuture<Integer> result = grid.execute(GridHelloWorldTask.class, source);

		assertThat(result.get(), is(removeWhiteSpaceFrom(source).length()));
	}

	@Before
	public void startGridNode() throws GridException {
		GridFactory.start();
	}

	@After
	public void stopGridNode() {
		GridFactory.stop(true);
	}

	private String removeWhiteSpaceFrom(final String source) {
		return source.replaceAll("\\s", "");
	}
}