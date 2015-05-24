package com.imac;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class mapper extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] token = value.toString().split(";");
		for (String words : token) {
			if(words.contains("plist") && words.length()>6){
				String[] plist = words.split(",");
				for (int i=0 ; i <plist.length ;i=i+3) {
						if(plist[i].contains("plist")){
							word.set(plist[i].split("=")[1]);
							one.set((Integer.parseInt(plist[i+1])*Integer.parseInt(plist[i+2])));
							context.write(word, one);
						}else{
							if((i+2)<plist.length){
								word.set(plist[i]);
								one.set((Integer.parseInt(plist[i+1])*Integer.parseInt(plist[i+2])));
								context.write(word, one);
							}
						
						}
				
				}
			}

		}
	}
}

