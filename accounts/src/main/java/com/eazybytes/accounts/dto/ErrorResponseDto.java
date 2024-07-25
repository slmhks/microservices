package com.eazybytes.accounts.dto;

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
            description = "API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error that occurred"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message describing the error that occurred"
    )
    private String errorMessage;

    @Schema(
            description = "Time describing when the error occurred"
    )
    private LocalDateTime errorTime;
}
