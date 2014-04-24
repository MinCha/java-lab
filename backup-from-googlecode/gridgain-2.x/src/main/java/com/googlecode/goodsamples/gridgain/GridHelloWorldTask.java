package com.googlecode.goodsamples.gridgain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gridgain.grid.GridException;
import org.gridgain.grid.GridJob;
import org.gridgain.grid.GridJobAdapter;
import org.gridgain.grid.GridJobResult;
import org.gridgain.grid.GridTaskSplitAdapter;

@SuppressWarnings("serial")
public class GridHelloWorldTask extends GridTaskSplitAdapter<String, Integer> {
	private static final Log LOG = LogFactory.getLog(GridHelloWorldTask.class);

	@Override
	protected Collection<? extends GridJob> split(int gridSize, String arg)
			throws GridException {
		String[] words = arg.split(" ");

		List<GridJob> jobs = new ArrayList<GridJob>(words.length);

		for (final String eachWord : words) {
			jobs.add(new GridJobAdapter<String>(eachWord) {
				public Serializable execute() {
					String word = getArgument();
					LOG.info("EachWord : " + eachWord);
					return word.length();
				}
			});
		}
		
		return jobs;
	}

	@Override
	public Integer reduce(List<GridJobResult> results) throws GridException {
		int totalCharCnt = 0;
        
        for (GridJobResult res : results) {
            Integer charCnt = res.getData();
            totalCharCnt += charCnt;
        }
        
        return totalCharCnt;
    }
}