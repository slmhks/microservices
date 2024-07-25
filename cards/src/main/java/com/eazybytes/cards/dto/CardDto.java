package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(
        name = "Card",
        description = "Schema to hold card information"
)
@Data
@NoArgsConstructor
public class CardDto {

    @Schema(
            description = "Mobile number", example = "9876543210"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Card number", example = "1234123412341234"
    )
    @Pattern(regexp = "(^$|[0-9]{16})", message = "Card number must have 16 digits.")
    private String cardNumber;

    @Schema(
            description = "Card type", example = "CREDIT, DEBIT, REWARDS"
    )
    @NotEmpty(message = "Card type cannot be empty.")
    @Size(min = 3, max = 20, message = "The card type length must be between 3 and 30.")
    private String cardType;

    @Schema(
            description = "Total Limit of card in case its type is CREDIT", example = "2500.00"
    )
    @DecimalMin(value = "500.00", inclusive = true, message = "The minimum valid amount is 500.00.")
    @DecimalMax(value = "3000.00", inclusive = true, message = "The maximum valida amount is 3000.00.")
    private BigDecimal totalLimit;

    @Schema(
            description = "Amount used", example = "1200.00"
    )
    @DecimalMin(value = "1.00", inclusive = true, message = "The minimum valid amount is 1.00.")
    @DecimalMax(value = "3000.00", inclusive = true, message = "The maximum valid amount is 3000.00")
    private BigDecimal amountUsed;

    @Schema(
            description = "Available amount", example = "1300.00"
    )
    @DecimalMin(value = "1.00", inclusive = true, message = "The minimum valid amount is 1.00.")
    @DecimalMin(value = "1.00", inclusive = true)
    private BigDecimal availableAmount;
}
