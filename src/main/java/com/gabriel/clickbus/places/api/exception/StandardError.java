package com.gabriel.clickbus.places.api.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError {

    private int status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;

    private List<FieldError> fields;

    @Getter
    @Builder
    public static class FieldError {
        private String name;
        private String message;
    }


}
