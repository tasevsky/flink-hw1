package window_utils;

import model.InputPOJO;
import model.pojo.MessageCountingResult;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.DoubleSummaryStatistics;

public class MessageStatsWindowFunction implements WindowFunction<InputPOJO, String, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow timeWindow,
                      Iterable<InputPOJO> iterable, Collector<String> collector) {
        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
        iterable.forEach(record -> statistics.accept(record.getValue()));
        collector.collect(String.format("{\"key\":\"" + key +
                "\", \"window_start\":" + timeWindow.getStart() +
                ", \"window_end\":" + timeWindow.getEnd() +
                ", \"min_value\":" + statistics.getMin() +
                ", \"count\":" + statistics.getCount() +
                ", \"average\":" + statistics.getAverage() +
                ", \"max_value\":" + statistics.getMax() + "}"));
    }
}
