package source.schemas;

import org.apache.flink.api.common.serialization.SerializationSchema;

public class StringSerializationSchema implements SerializationSchema<String> {
    @Override
    public byte[] serialize(String element) {
        return element.getBytes();
    }
}
