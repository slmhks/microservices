package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;

@Schema(
        name = "Response",
        description = "Schema to hold information for a successful response"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "HTTP Status Code"
    )
    private String status;
    @Schema(
            description = "Successful response message"
    )
    private String statusMsg;
}
