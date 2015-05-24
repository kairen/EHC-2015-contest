package com.imac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	private Text key = new Text();
	private ArrayList<Integer> arrayListValue = new ArrayList<Integer>(); 
	private ArrayList<String> arrayListKey= new ArrayList<String>();
    
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		arrayListValue.add(sum);
		arrayListKey.add(key.toString());

	}
	
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		Collections.sort(arrayListValue);
		for(int i=0 ; i<arrayListValue.size() ;i++){
			result.set(arrayListValue.get(i));
			key.set(arrayListKey.get(i));
			context.write(key, result);
		}

	}
	
}
