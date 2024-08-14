package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer Details (accounts, cards and loans)",
        externalDocs = @ExternalDocumentation(
                description = "Use this link to get further information about this schema.",
                url = "http://www.test.com/api/schemas"
        )
)
public class CustomerDetailsDto {

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

    private CustomerDto customerDto;
    private CardDto cardDto;
    private LoansDto loansDto;

}
