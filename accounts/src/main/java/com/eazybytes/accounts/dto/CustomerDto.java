package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Customer",
        description = "Schema to hold customer information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30.")
    private String name;

    @Schema(
            description = "Customer's email", example = "tutor@test.com"
    )
    @NotEmpty(message = "Email address cannot be null or empty.")
    @Email(message = "Email address should be a valid value.")
    private String email;

    @Schema(
            description = "Customer's mobile number", example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    @Valid
    private AccountsDto accountsDto;
}
