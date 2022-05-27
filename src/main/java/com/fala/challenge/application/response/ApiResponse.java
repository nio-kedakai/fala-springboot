package com.fala.challenge.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse {
    private Integer code;
    private String type;
    private String message;
}
