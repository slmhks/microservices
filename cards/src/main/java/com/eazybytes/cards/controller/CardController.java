package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardConstants;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Cards in Eazy Bank",
        description = "CRUD REST APIs in Eazy Bank to CREATE, FETCH, UPDATE and DELETE card details"
)
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@Validated
public class CardController {

    private ICardService service;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create card details in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    /*@PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestBody CardDto cardDto) {
        this.service.createCard(cardDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }*/
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(
            @Parameter(
                    description = "Mobile number",
                    example = "9876543210",
                    required = true
            )
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits!")
            @RequestParam String mobileNumber) {
        this.service.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch card details from Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @GetMapping(path = "/fetch")
    public ResponseEntity<CardDto> fetchCard(
            @Parameter(
                    description = "Card number must have 10 digits.",
                    example = "9876543210",
                    required = true
            )
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
            String mobileNumber) {
        CardDto cardDto = this.service.fetchCard(mobileNumber);
        return ResponseEntity
                .ok()
                .body(cardDto);
    }

    @Operation(
            summary = "Update Card REST API",
            description = "REST API to update card details in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto) {
        if (this.service.updateCard(cardDto)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete the card details in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCard(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
            String mobileNumber) {
        if (this.service.deleteCard(mobileNumber)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "DELETE All Cards API",
            description = "REST API to delete all cards associated to a mobile number in Eazy Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<ResponseDto> deleteAllCards(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
            String mobileNumber) {
        if (this.service.deleteAllCardsForMobileNumber(mobileNumber)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }
}
