package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST API for Customer Details in Eazy Bank",
        description = "REST API to retrieve Customer Details in Eazy Bank, which includes customer and its accounts, cards and loans",
        externalDocs = @ExternalDocumentation(
                description = "Please, go to the following web site to get more details about how this API works.",
                url = "http://www.test.com/api/customerdetails"
        )
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomersService service;

    public CustomerController(ICustomersService service) {
        this.service = service;
    }

    @Operation(
            summary = "REST API to get customer details",
            description = "REST API to get customer details in Eazy Bank. It includes customer and its associated accounts, cards and loans."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK",
                    content = @Content(
                            schema = @Schema(
                                    implementation = CustomerDetailsDto.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    })
    @GetMapping(path = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(
            @RequestHeader("eazybank-correlation-id") String correlationId,
            @Parameter(
                    description = "Mobile number composed of 10 digits",
                    example = "9876543210",
                    required = true
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
            @RequestParam String mobileNumber) {
        logger.debug("eazybank-correlation-id found: {}", correlationId);
        CustomerDetailsDto customerDetailsDto = this.service.fetchCustomerDetails(correlationId, mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
