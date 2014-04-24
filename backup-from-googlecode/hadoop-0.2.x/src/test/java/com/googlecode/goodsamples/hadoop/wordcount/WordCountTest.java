package com.googlecode.goodsamples.hadoop.wordcount;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Before;
import org.junit.Test;

public class WordCountTest extends AbstractMapReduceTest {
	private static final String INPUT_PATH = "src/test/resources/wordcount/input";
	static final String OUTPUT_PATH = "src/test/resources/wordcount/output";
	private final Path input = new Path(INPUT_PATH);
	private final Path output = new Path(OUTPUT_PATH);

	@Before
	public void assertThatPrerequisitesAreSatisfied() throws Exception {
		fullyDeleteIfExists(OUTPUT_PATH);
		assertThat(new File(INPUT_PATH).exists(), is(true));
	}

	@Test
	public void executeWordCountJobOnHadoop() throws Exception {
	    Job job = configureJob();
	    job.waitForCompletion(true);
	}

	private Job configureJob() throws IOException {
		Job job = new Job(new Configuration(), WordCountTest.class.getSimpleName());
	    job.setMapperClass(WordCountMapper.class);
	    job.setReducerClass(WordCountReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    
	    FileInputFormat.addInputPath(job, input);
	    FileOutputFormat.setOutputPath(job, output);
		return job;
	}
}
