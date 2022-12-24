package window_utils;

import model.InputPOJO;
import model.pojo.MessageCountingResult;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class MessageCountingWindowFunction implements WindowFunction<InputPOJO, MessageCountingResult, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow window, Iterable<InputPOJO> input, Collector<MessageCountingResult> out) throws Exception {
        MessageCountingAccumulator accumulator = new MessageCountingAccumulator();

        for (InputPOJO message: input)
            accumulator = accumulator.add(message);

        MessageCountingResult result = accumulator.getResult();
        result.setStartTimestamp(window.getStart());
        result.setEndTimestamp(window.getEnd());

        out.collect(result);
    }
}