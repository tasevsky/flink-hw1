package source.schemas;

import model.OutputPOJO;
import org.apache.flink.api.common.serialization.SerializationSchema;

public class OutputPojoSerializationSchema implements SerializationSchema<OutputPOJO> {

    @Override
    public byte[] serialize(OutputPOJO outputPOJO) {
        return outputPOJO.toString().getBytes();
    }
}
