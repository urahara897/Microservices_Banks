package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description= "Account Information"
)
@Data
public class AccountsDto {
    @Schema(
            description = "Account Number",
            example = "1234567890"
    )
    @Pattern(regexp="(^$|[0-9]{10})",message = "Account number must be 10 digits")
    @NotEmpty(message = "Account number should not be empty")
    private Long accountNumber;
    @Schema(
            description = "Account Type",
            example = "Savings"
    )
    @NotEmpty(message = "Account type should not be empty")
    private String accountType;
    @Schema(
            description = "Branch Address",
            example = "567 Chandni Chowk, Mumbai"
    )
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}
