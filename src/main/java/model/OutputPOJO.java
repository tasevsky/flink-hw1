package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class OutputPOJO {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("count")
    @Expose
    private Integer count;

    public OutputPOJO(String key, Integer count) {
        this.key = key;
        this.count = count;
    }

    public String getKey() {
        return key;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{ \"key\": " + key + ", count: " + count + "}";
    }
}