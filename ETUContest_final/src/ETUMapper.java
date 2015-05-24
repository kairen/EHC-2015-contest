import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;


/**
 * Created by kairenbai on 2015/4/27.
 */

public class ETUMapper extends Mapper<Object, Text, Text, LongWritable> {

    private LongWritable price = new LongWritable();
    private Text pid = new Text();

    private  final static String indexKey = "plist=";

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String []values = value.toString().split(indexKey);
        String []items = values[1].split(",");
        if(items.length >= 3) {
            for (int i = 0; i < items.length; i += 3) {
                pid.set(items[i]);
                price.set(Integer.parseInt(items[i + 1]) * Integer.parseInt(items[i + 2]));
                context.write(pid, price);
            }
        }
    }
}

