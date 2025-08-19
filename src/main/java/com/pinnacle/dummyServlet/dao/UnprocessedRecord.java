package com.itextos.beacon.httpclienthandover.clevertap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents an item in the 'unprocessed' list.
 * Note: The 'record' field can be a complex object or a simple string.
 * We handle this by mapping it to Jackson's generic JsonNode.
 */
public record UnprocessedRecord(
        String status,
        int code,
        String error,
        JsonNode record // Using JsonNode for flexibility as 'record' can be an object or a string
) {
    @JsonCreator
    public UnprocessedRecord(
            @JsonProperty("status") String status,
            @JsonProperty("code") int code,
            @JsonProperty("error") String error,
            @JsonProperty("record") JsonNode record
                            ) {
        this.status = status;
        this.code = code;
        this.error = error;
        this.record = record;
    }
}
