package com.virus7x.hotelservice.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
