/**
 * Created by kairenbai on 2015/4/27.
 */
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ETURunner {

    private final static int spliteSize = 745160;

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        conf.setInt("mapred.tasktracker.map.tasks.maximum", 4);
        conf.setFloat("mapred.job.reduce.input.buffer.percent", (float)0.70);
        conf.setInt("mapred.inmem.merge.threshold", 0);
        conf.setInt("io.sort.mb", 2);

        conf.setInt("mapreduce.map.memory.mb", 2048);
        conf.setInt("mapred.map.cpu.vcores", 4);

        Job job = Job.getInstance(conf, "ETU");

        job.setJarByClass(ETUReducer.class);
        job.setMapperClass(ETUMapper.class);
        job.setReducerClass(ETUReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
//        job.setSortComparatorClass(ETUComparator.class);

        FileInputFormat.setMinInputSplitSize(job, spliteSize);
        FileInputFormat.setMaxInputSplitSize(job, spliteSize);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setNumReduceTasks(1);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
