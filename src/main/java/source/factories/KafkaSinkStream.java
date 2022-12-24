package source.factories;

import model.OutputPOJO;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import settings.ProjectSettings;
import source.schemas.OutputPojoSerializationSchema;
import source.schemas.StringSerializationSchema;

public class KafkaSinkStream {

    public static SinkFunction<OutputPOJO> getKafkaSink1(ParameterTool parameterTool) {
        String resultTopic = parameterTool.get(
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC_NAME_1,
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC_1
        );
        return new FlinkKafkaProducer<>(
                resultTopic,
                new OutputPojoSerializationSchema(),
                parameterTool.getProperties()
        );
    }

    public static SinkFunction<String> getKafkaSink2(ParameterTool parameterTool) {
        String resultTopic = parameterTool.get(
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC_NAME_2,
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC_2
        );
        return new FlinkKafkaProducer<>(
                resultTopic,
                new StringSerializationSchema(),
                parameterTool.getProperties()
        );
    }
}