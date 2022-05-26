package com.fala.challenge.application.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {

    private int statusCode;
    private String localDateTimeNow;
    private String message;
    private String description;
}
