package domasna_flink;


import com.google.gson.Gson;
import model.InputPOJO;
import model.OutputPOJO;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import source.factories.SinkFactory;
import source.factories.SourceDataStreamFactory;
import timestamp_utils.InputPOJOTimestampAndWatermarkStrategy;
import window_utils.MessageCountingWindowFunction;
import window_utils.MessageStatsWindowFunction;

import java.util.DoubleSummaryStatistics;

// Zadaca 2:
public class KafkaProcess2 {

    public static void main(String [] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        DataStream<InputPOJO> inputMessageDataStream = SourceDataStreamFactory.getStream(parameterTool, env)
                .map(value -> new Gson().fromJson(String.valueOf(value), InputPOJO.class));
        // inputMessageDataStream.print();

        SingleOutputStreamOperator<String> output = inputMessageDataStream
                .assignTimestampsAndWatermarks(new InputPOJOTimestampAndWatermarkStrategy())
                .keyBy(InputPOJO::getKey)
                .window(SlidingEventTimeWindows.of(Time.seconds(5), Time.seconds(2)))
                .apply(new MessageStatsWindowFunction());

        output.print();

        output.addSink(SinkFactory.getSinkProcess2(parameterTool));

        env.execute(KafkaProcess2.class.getName());

    }
}