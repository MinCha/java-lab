package com.googlecode.goodsamples.hadoop.wordcount;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.mapred.MiniMRCluster;
import org.junit.After;
import org.junit.Before;

public class AbstractMapReduceTest {
	private final static int DATANODE_COUNT = 1;
	private final static String LOG_PATH = "test-logs";
	protected MiniDFSCluster dfsCluster = null;
	protected MiniMRCluster mapReduceCluster = null;

	@Before
	public void startMapReduceCluster() throws Exception {
		new File(LOG_PATH).mkdirs();
		System.setProperty("hadoop.log.dir", LOG_PATH);
		dfsCluster = new MiniDFSCluster(new Configuration(), DATANODE_COUNT, true, null);
		mapReduceCluster = new MiniMRCluster(1, getFileSystem().getUri()
				.toString(), 1);
	}

	protected FileSystem getFileSystem() throws IOException {
		return dfsCluster.getFileSystem();
	}

	@After
	public void stopMapReduceCluster() throws Exception {
		if (dfsCluster != null) {
			dfsCluster.shutdown();
			dfsCluster = null;
		}

		if (mapReduceCluster != null) {
			mapReduceCluster.shutdown();
			mapReduceCluster = null;
		}
	}

	protected void fullyDeleteIfExists(String path) throws IOException {
		FileUtil.fullyDelete(new File(path));
	}
}
