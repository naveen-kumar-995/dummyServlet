package com.pinnacle.dummyServlet.dao;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the 'record' object, which can be part of an unprocessed item.
 */
public record RecordData(
        String event,
        List<DataDetail> data
) {
    @JsonCreator
    public RecordData(
            @JsonProperty("event") String event,
            @JsonProperty("data") List<DataDetail> data
                     ) {
        this.event = event;
        this.data = data;
    }
}
