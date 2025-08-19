package com.itextos.beacon.httpclienthandover.clevertap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the innermost data object within the 'record'.
 * Using @JsonCreator and @JsonProperty for clear mapping from JSON fields to record components.
 */
public record DataDetail(
        long ts,
        String meta,
        String code,
        String description

) {
    @JsonCreator
    public DataDetail(
            @JsonProperty("ts") long ts,
            @JsonProperty("meta") String meta,
            @JsonProperty("code") String code,
            @JsonProperty("description") String description
                     ) {
        this.ts = ts;
        this.meta = meta;
        this.code = code;
        this.description = description;
    }
}
