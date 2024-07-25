package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

    @Schema(
            description = "Account Number of Eazy Bank account", example = "9876543210"
    )
    @NotNull(message = "Account number cannot be null or empty.")
    //@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits.")
    @Min(value = 1000000000L, message = "Account number must be 10 digits.")
    @Max(value = 9999999999L, message = "Account number must be 10 digits.")
    private Long accountNumber;

    @Schema(
            description = "Account Type of Eazy Bank account", example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty.")
    private String accountType;

    @Schema(
            description = "Eazy Bank branch address", example = "123, Helmut Street, New York"
    )
    @NotEmpty(message = "Branch address cannot be null or empty.")
    private String branchAddress;
}
