package domasna_flink;

import com.google.gson.Gson;
import model.InputPOJO;
import model.OutputPOJO;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import source.factories.SinkFactory;
import source.factories.SourceDataStreamFactory;
import timestamp_utils.InputPOJOTimestampAndWatermarkStrategy;
import window_utils.MessageCountingWindowFunction;

// Zadaca 1:
public class KafkaProcess1 {

    public static void main(String [] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        DataStream<InputPOJO> inputMessageDataStream = SourceDataStreamFactory.getStream(parameterTool, env)
                .map(value -> new Gson().fromJson(String.valueOf(value), InputPOJO.class));

        SingleOutputStreamOperator<OutputPOJO> output = inputMessageDataStream
                .assignTimestampsAndWatermarks(new InputPOJOTimestampAndWatermarkStrategy())
                .keyBy(InputPOJO::getKey)
                .window(SlidingEventTimeWindows.of(Time.seconds(5), Time.seconds(2)))
                .apply(new MessageCountingWindowFunction())
                .map(value -> new Gson().fromJson(String.valueOf(value), OutputPOJO.class));

        output.print();

        output.addSink(SinkFactory.getSinkProcess1(parameterTool));

        env.execute(KafkaProcess1.class.getName());
    }
}
