package com.googlecode.goodsamples.hadoop.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer words = new StringTokenizer(value.toString(), " ");
		while(words.hasMoreTokens()) {
			Text word = new Text(words.nextToken());
			IntWritable count = new IntWritable(1);
			
			context.write(word, count);
		}
	}
}
