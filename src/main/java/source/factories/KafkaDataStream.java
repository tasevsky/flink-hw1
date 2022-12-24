package source.factories;

import model.InputMessage;
import model.pojo.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema;
import settings.ProjectSettings;
import source.schemas.ControlMessageDeserializationSchema;
import source.schemas.InputMessageDeserializationSchema;

public class KafkaDataStream {
    static DataStreamSource<InputMessage> getDataStream(ParameterTool parameterTool,
                                                        StreamExecutionEnvironment env) {
        String topic = parameterTool.get(
                ProjectSettings.DEFAULT_MESSAGE_TOPIC_NAME,
                ProjectSettings.DEFAULT_MESSAGE_TOPIC
        );

        FlinkKafkaConsumer<InputMessage> kafkaConsumer = new FlinkKafkaConsumer<>(
                topic,
                new InputMessageDeserializationSchema(),
                parameterTool.getProperties());

        return env.addSource(kafkaConsumer);
    }
}