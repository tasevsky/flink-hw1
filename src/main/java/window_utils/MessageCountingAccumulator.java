package window_utils;

import model.InputPOJO;
import model.pojo.MessageCountingResult;

public class MessageCountingAccumulator {
    String key;
    Integer value;
    Long timestamp;
    long count = 0;

    public MessageCountingAccumulator() {

    }

    public MessageCountingAccumulator(String key, Integer value, Long timestamp, long count) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.count = count;
    }

    public MessageCountingAccumulator add(InputPOJO value) {
        return new MessageCountingAccumulator(
                value.getKey(),
                value.getValue(),
                value.getTimestamp(),
                this.count+1
        );
    }

    public MessageCountingResult getResult() {
        MessageCountingResult result = new MessageCountingResult();
        result.setKey(this.key);
        result.setValue(this.value);
        result.setTimestamp(this.timestamp);
        result.setCount(this.count);
        return result;
    };

    public MessageCountingAccumulator merge(MessageCountingAccumulator other) {
        return new MessageCountingAccumulator(
                this.key,
                this.value,
                this.timestamp,
                this.count+other.count
        );
    }
}