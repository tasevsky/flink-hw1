package source.factories;

import model.InputMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import settings.ProjectSettings;
public class SourceDataStreamFactory {

    public static DataStreamSource<InputMessage> getStream(ParameterTool parameterTool, StreamExecutionEnvironment env) {

        boolean testMode = parameterTool.getBoolean(
                ProjectSettings.TEST_MODE,
                ProjectSettings.DEFAULT_TEST_MODE
        );

        if (testMode) {
            //return env.addSource(new ExampleInputMessageSource());
            return KafkaDataStream.getDataStream(parameterTool, env);
        } else {
            return KafkaDataStream.getDataStream(parameterTool, env);
        }
    }



}