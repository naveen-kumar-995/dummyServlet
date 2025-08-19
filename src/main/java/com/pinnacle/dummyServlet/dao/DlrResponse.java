package com.itextos.beacon.httpclienthandover.clevertap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the top-level structure of the customer's DLR response.
 */
public record DlrResponse(
        String status,
        long processed,
        List<UnprocessedRecord> unprocessed
) {
    @JsonCreator
    public DlrResponse(
            @JsonProperty("status") String status,
            @JsonProperty("processed") long processed,
            @JsonProperty("unprocessed") List<UnprocessedRecord> unprocessed
                      ) {
        this.status = status;
        this.processed = processed;
        this.unprocessed = unprocessed;
    }
}
