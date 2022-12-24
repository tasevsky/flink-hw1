package model.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class MessageCountingResult implements Serializable
{
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("count")
    @Expose
    private Long count;
    @SerializedName("start_timestamp")
    @Expose
    private Long startTimestamp;
    @SerializedName("end_timestamp")
    @Expose
    private Long endTimestamp;
    private final static long serialVersionUID = -1215532553943819953L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MessageCountingResult() {
    }

    /**
     *
     * @param logType
     * @param system
     * @param count
     * @param endTimestamp
     * @param startTimestamp
     */
    public MessageCountingResult(String key, Integer value, Long timestamp, Long count, Long startTimestamp, Long endTimestamp) {
        super();
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.count = count;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageCountingResult that = (MessageCountingResult) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value) && Objects.equals(timestamp, that.timestamp) && Objects.equals(count, that.count) && Objects.equals(startTimestamp, that.startTimestamp) && Objects.equals(endTimestamp, that.endTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, timestamp, count, startTimestamp, endTimestamp);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, MessageCountingResult.class);
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}