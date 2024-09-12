package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by the client"
    )
    private String apiPath;
    @Schema(
            description = "HTTP Status Code for the error"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Description of the error that occurred"
    )
    private String errorMessage;
    @Schema(
            description = "Time on which the error occurred"
    )
    private LocalDateTime errorTime;
}
